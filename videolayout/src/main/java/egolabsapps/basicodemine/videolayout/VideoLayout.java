package egolabsapps.basicodemine.videolayout;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.HashMap;

public class VideoLayout extends FrameLayout implements TextureView.SurfaceTextureListener {

    private String FILE_NAME;
    private int VIDEO_GRAVITY;
    private TextureView videoSurface;
    private float mVideoWidth;
    private float mVideoHeight;
    private String TAG = "VideoLayout";
    private MediaPlayer mMediaPlayer;
    private boolean isUrl;
    private boolean IS_LOOP;
    private boolean SOUND;


    // <enum name="start" value="0" />
    // <enum name="end" value="1" />
    // <enum name="centerCrop" value="2" />
    // <enum name="fitXY" value="3" />
    // <enum name="centerInside" value="3" />

    public static enum VGravity {
        start,
        end,
        centerCrop,
        fitXY,
        centerInside;

        public int getValue() {
            switch (this) {
                case end:
                    return 1;
                case start:
                    return 0;
                case centerCrop:
                    return 2;
                case centerInside:
                    return 4;
                case fitXY:
                default:
                    return 3;
            }
        }
    }

    public VideoLayout(@NonNull Context context) {
        super(context);
    }

    //*you can also get mediaplayer and set it manually*/
    public void setSound(boolean sound) {
        this.SOUND = sound;
        if (mMediaPlayer != null)
            try {
                if (!sound) {
                    mMediaPlayer.setVolume(0f, 0f);
                } else {
                    mMediaPlayer.setVolume(.5f, .5f);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public VideoLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VideoLayout, 0, 0);
        try {
            FILE_NAME = a.getString(R.styleable.VideoLayout_path_or_url);
            VIDEO_GRAVITY = a.getInteger(R.styleable.VideoLayout_video_gravity, 2);
            IS_LOOP = a.getBoolean(R.styleable.VideoLayout_loop, true);
            SOUND = a.getBoolean(R.styleable.VideoLayout_sound, false);
        } finally {
            a.recycle();
        }

        if (TextUtils.isEmpty(FILE_NAME)) {
            return;
        }

        isUrl = FILE_NAME.contains("http://") || FILE_NAME.contains("https://");
        initViews();
        addView(videoSurface);
        setListeners();

        if (VIDEO_GRAVITY != 3) {
            calculateVideoSize();
            surfaceSetup();
        }

    }


    private void initViews() {
        videoSurface = new TextureView(getContext());
    }

    private void setListeners() {
        videoSurface.setSurfaceTextureListener(this);
    }

    private void calculateVideoSize() {
        try {
            MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
            if (isUrl)
                metaRetriever.setDataSource(FILE_NAME, new HashMap<>());
            else {
                AssetFileDescriptor afd = getContext().getAssets().openFd(FILE_NAME);
                metaRetriever.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            }
            String height = metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
            String width = metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
            mVideoHeight = Float.parseFloat(height);
            mVideoWidth = Float.parseFloat(width);
            metaRetriever.release();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void updateTextureViewSize(int viewWidth, int viewHeight) {
        float scaleX = 1.0f;
        float scaleY = 1.0f;

        int pivotPointX = (VIDEO_GRAVITY == 0) ? 0 : (VIDEO_GRAVITY == 1) ? viewWidth : viewWidth / 2;
        int pivotPointY = viewHeight / 2;

        Matrix matrix = new Matrix();

        if (VIDEO_GRAVITY == 4) {
            pivotPointX = 0;
            pivotPointY = viewHeight / 2;

            if (mVideoWidth > viewWidth && mVideoHeight > viewHeight) {
                scaleX = mVideoWidth / viewWidth;
                scaleY = mVideoHeight / viewHeight;
            } else if (mVideoWidth < viewWidth && mVideoHeight < viewHeight) {
                scaleY = viewWidth / mVideoWidth;
                scaleX = viewHeight / mVideoHeight;
            }

            scaleY = scaleY / 2;
            scaleX = scaleX / 2;

            matrix.setScale(scaleX, scaleY, pivotPointX, pivotPointY);
        } else {
            if (mVideoWidth > viewWidth && mVideoHeight > viewHeight) {
                scaleX = mVideoWidth / viewWidth;
                scaleY = mVideoHeight / viewHeight;
            } else if (mVideoWidth < viewWidth && mVideoHeight < viewHeight) {
                scaleY = viewWidth / mVideoWidth;
                scaleX = viewHeight / mVideoHeight;
            } else if (viewWidth > mVideoWidth) {
                scaleY = (viewWidth / mVideoWidth) / (viewHeight / mVideoHeight);
            } else if (viewHeight > mVideoHeight) {
                scaleX = (viewHeight / mVideoHeight) / (viewWidth / mVideoWidth);
            }

            matrix.setScale(scaleX, scaleY, pivotPointX, pivotPointY);
        }


        videoSurface.setTransform(matrix);
        videoSurface.setLayoutParams(new FrameLayout.LayoutParams(viewWidth, viewHeight));
    }


    private void surfaceSetup() {
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        updateTextureViewSize(screenWidth, screenHeight);
    }

    private void surfaceAvailableWorkers(SurfaceTexture surfaceTexture) {
        Surface surface = new Surface(surfaceTexture);

        try {
            mMediaPlayer = new MediaPlayer();
            if (isUrl)
                mMediaPlayer.setDataSource(FILE_NAME);
            else {
                AssetFileDescriptor afd = getContext().getAssets().openFd(FILE_NAME);
                mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            }

            if (!SOUND)
                mMediaPlayer.setVolume(0f, 0f);


            mMediaPlayer.setSurface(surface);
            mMediaPlayer.setLooping(IS_LOOP);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(MediaPlayer::start);

        } catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException ignored) {

        }
    }

    private void changeVideo() {
        try {
            onDestroyVideoLayout();
            mMediaPlayer = new MediaPlayer();
            if (isUrl)
                mMediaPlayer.setDataSource(FILE_NAME);
            else {
                AssetFileDescriptor afd = getContext().getAssets().openFd(FILE_NAME);
                mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            }

            if (!SOUND)
                mMediaPlayer.setVolume(0f, 0f);

            mMediaPlayer.setLooping(IS_LOOP);
            mMediaPlayer.setSurface(new Surface(videoSurface.getSurfaceTexture()));
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(MediaPlayer::start);

        } catch (IllegalArgumentException | IOException | IllegalStateException | SecurityException ignored) {

        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        surfaceAvailableWorkers(surface);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }


    public void onDestroyVideoLayout() {
        if (mMediaPlayer != null) {
            try {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = null;
            } catch (IllegalStateException e) {
            }

        }
    }

    public void onResumeVideoLayout() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying())
            try {
                mMediaPlayer.start();
            } catch (IllegalStateException ignored) {

            }
    }

    public void onPauseVideoLayout() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying())
            try {
                mMediaPlayer.pause();
            } catch (IllegalStateException ignored) {

            }
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    public TextureView getVideoSurface() {
        return videoSurface;
    }

    public void setPathOrUrl(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;

        isUrl = FILE_NAME.contains("http://") || FILE_NAME.contains("https://");

        if (videoSurface == null) {
            initViews();
            addView(videoSurface);
            setListeners();
        }

        if (VIDEO_GRAVITY != 3) {
            calculateVideoSize();
            surfaceSetup();
        }

        if (videoSurface != null) {
            changeVideo();
        }
    }

    public void setIsLoop(boolean IS_LOOP) {
        this.IS_LOOP = IS_LOOP;
    }

    public void setGravity(VGravity gravity) {
        VIDEO_GRAVITY = gravity.getValue();
    }
}
