package egolabsapps.basicodemine.videolayout;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;

import java.io.IOException;
import java.util.HashMap;

public class VideoLayout extends FrameLayout implements TextureView.SurfaceTextureListener {

    private String FILE_NAME;
    private int VIDEO_GRAVITY;
    private TextureView videoSurface;
    float mVideoWidth;
    float mVideoHeight;
    private String TAG = "VideoLayout";
    private MediaPlayer mMediaPlayer;
    private boolean isUrl;
    private boolean IS_LOOP;

    public VideoLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VideoLayout, 0, 0);
        try {
            FILE_NAME = a.getString(R.styleable.VideoLayout_path_or_url);
            VIDEO_GRAVITY = a.getInteger(R.styleable.VideoLayout_video_gravity, 2);
            IS_LOOP = a.getBoolean(R.styleable.VideoLayout_loop, true);
        } finally {
            a.recycle();
        }

        isUrl = FILE_NAME.contains("http://") || FILE_NAME.contains("https://");
        initViews();
        addView(videoSurface);
        setListeners();

        /*
         *  <enum name="left" value="0"/>
         *  <enum name="right" value="1"/>
         *  <enum name="centerCrop" value="2"/>
         *  <enum name="none" value="3"/>
         *
         * */

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
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateTextureViewSize(int viewWidth, int viewHeight) {
        float scaleX = 1.0f;
        float scaleY = 1.0f;

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

        int pivotPointX = (VIDEO_GRAVITY == 0) ? 0 : (VIDEO_GRAVITY == 1) ? viewWidth : viewWidth / 2;
        int pivotPointY = viewHeight / 2;

        Matrix matrix = new Matrix();
        matrix.setScale(scaleX, scaleY, pivotPointX, pivotPointY);

        videoSurface.setTransform(matrix);
        videoSurface.setLayoutParams(new FrameLayout.LayoutParams(viewWidth, viewHeight));
    }


    private void surfaceSetup() {
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        updateTextureViewSize(screenWidth, screenHeight);
    }

    private void surfaceAvaibleWorkers(SurfaceTexture surfaceTexture, int width, int height) {
        Surface surface = new Surface(surfaceTexture);

        try {
            mMediaPlayer = new MediaPlayer();
            if (isUrl)
                mMediaPlayer.setDataSource(FILE_NAME);
            else {
                AssetFileDescriptor afd = getContext().getAssets().openFd(FILE_NAME);
                mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            }
            mMediaPlayer.setVolume(0f, 0f);
            mMediaPlayer.setSurface(surface);
            mMediaPlayer.setLooping(IS_LOOP);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(MediaPlayer::start);

        } catch (IllegalArgumentException e) {
            Log.d(TAG, e.getMessage());
//            e.printStackTrace();
        } catch (SecurityException e) {
            Log.d(TAG, e.getMessage());
//            e.printStackTrace();
        } catch (IllegalStateException e) {
            Log.d(TAG, e.getMessage());
//            e.printStackTrace();
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
//            e.printStackTrace();
        }
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        surfaceAvaibleWorkers(surface, width, height);
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
                Log.d(TAG, e.getMessage());
            }

        }
    }

    public void onResumeVideoLayout() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying())
            try {
                mMediaPlayer.start();
            } catch (IllegalStateException e) {
                Log.d(TAG, e.getMessage());
            }
    }

    public void onPauseVideoLayout() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying())
            try {
                mMediaPlayer.pause();
            } catch (IllegalStateException e) {
                Log.d(TAG, e.getMessage());
            }
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    public TextureView getVideoSurface() {
        return videoSurface;
    }
}
