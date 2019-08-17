# VideoLayout
> More stylish background designs
> Performance friendly and scaleable

### Why this project exist ?
The normal videoview on Android is very costly in terms of performance. Also, due to the video resolution ratio and apk size, most developers don't want to include videos in the project. With this library, developers will have a performance-friendly video background with a few lines of code.

## Features and Usage

### Easy implementation 

`view` can be any Button, Imageview or ImageButton which activate the FlyingVideo. `videoId` should have an ID of your YouTube video. For simply:

```java
  FlyingVideo.get(MainActivity.this)
                .setFloatMode(TaskCoffeeVideo.FLOAT_MOVE.STICKY)
                .setVideoStartSecond((mTracker == null) ? 0 : mTracker.getCurrentSecond())
                .coffeeVideoSetup(videoId)
                .show(view);
```
![](previews/untitledx1.gif) ![](previews/untitledx22.gif)


### Sticky and Free Mode

#### Sticky FLOAT_MOVE

FlyingVideo automatically snaps up or down according to the position of the screen where you left the panel.

```java
  FlyingVideo.get(MainActivity.this)
                .setFloatMode(TaskCoffeeVideo.FLOAT_MOVE.STICKY)
                .setVideoStartSecond((mTracker == null) ? 0 : mTracker.getCurrentSecond())
                .coffeeVideoSetup(videoId)
                .show(view);
```


#### Free FLOAT_MOVE

FlyingVideo never sticks on constant position, it stays on the position where you left.

```java
    FlyingVideo.get(MainActivity.this)
                .setFloatMode(TaskCoffeeVideo.FLOAT_MOVE.FREE)
                .setVideoStartSecond((mTracker == null) ? 0 : mTracker.getCurrentSecond())
                .coffeeVideoSetup(videoId)
                .show(view);
```

![Sticky](previews/untitledsticky.gif) ![Free](previews/untitlednosticky.gif)

### Initial FlyGravity Mode

#### BOTTOM FlyGravity


FlyVideo appears bottom of the screen when opened.

```java
    FlyingVideo.get(MainActivity.this)
                .setFloatMode(TaskCoffeeVideo.FLOAT_MOVE.FREE)
                .setVideoStartSecond((mTracker == null) ? 0 : mTracker.getCurrentSecond())
                .coffeeVideoSetup(videoId)
                .setFlyGravity(TaskCoffeeVideo.FLY_GRAVITY.BOTTOM)
                .show(view);
```

#### TOP FlyGravity

FlyVideo appears top of the screen when opened.

```java
    FlyingVideo.get(MainActivity.this)
                .setFloatMode(TaskCoffeeVideo.FLOAT_MOVE.FREE)
                .setVideoStartSecond((mTracker == null) ? 0 : mTracker.getCurrentSecond())
                .coffeeVideoSetup(videoId)
                .setFlyGravity(TaskCoffeeVideo.FLY_GRAVITY.TOP)
                .show(view);
```

![BOTTOM](previews/untitledbottom.gif) ![TOP](previews/untitledtop.gif)

## Implementation

###### Add it in your root build.gradle at the end of repositories:

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

###### Add the dependency

```
dependencies {
	        implementation 'com.github.AsynctaskCoffee:YoutubeFloatingVideo:0.0.3'
	}
```

### Used Libraries

* **Henning Dodenhof** - *Circular ImageView* - [hdodenhof](https://github.com/hdodenhof/CircleImageView)
* **Pierfrancesco Soffritti** - *android-youtube-player* - [PierfrancescoSoffritti](https://github.com/PierfrancescoSoffritti/android-youtube-player)

#### NOTE:

This library is designed not to violate the rules. However, all problems that may occur are under your responsibility.

Before publishing your app to GooglePlay please make sure that you have read Youtube and Google Terms;
* [Terms](https://developers.google.com/youtube/terms/developer-policies)



## License

```
   Copyright 2019 Egemen ÖZOGUL

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
