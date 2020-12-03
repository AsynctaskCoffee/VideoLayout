# VideoLayout :chicken: 



| [:cn: 中文](https://github.com/AsynctaskCoffee/VideoLayout/blob/master/README.cn.md) | [:kr: 한국어](https://github.com/AsynctaskCoffee/VideoLayout/blob/master/README.kr.md) | [:gb: English](https://github.com/AsynctaskCoffee/VideoLayout/blob/master/README.md) | [:tr: Türkçe](https://github.com/AsynctaskCoffee/VideoLayout/blob/master/README.tr.md) |
|-------------------------------------------------------------------------------------:|----------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------|

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ea90e5f54edc468eb5e6246f9fc806ed)](https://app.codacy.com/app/AsynctaskCoffee/VideoLayout?utm_source=github.com&utm_medium=referral&utm_content=AsynctaskCoffee/VideoLayout&utm_campaign=Badge_Grade_Dashboard) [![License](https://img.shields.io/badge/License-Apache%202.0-yellowgreen.svg)](https://opensource.org/licenses/Apache-2.0) ![version](https://img.shields.io/badge/version-1.3-blue) [![Gitter](https://badges.gitter.im/VideoLayout/community.svg)](https://gitter.im/VideoLayout/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)


![Preview](/previews/git.gif)

> More stylish background designs

> Performance friendly and scalable

## Why this project exists
The normal videoview on Android is very costly in terms of performance. Also, due to the video resolution ratio and apk size, most developers don't want to include videos in the project. With this library, developers will have a performance-friendly video background with a few lines of code.

## Features and Usage

### Easy implementation 

```java
        frameLayout = findViewById(R.id.frameLayout)
        videoLayout = VideoLayout(this)
        videoLayout.setGravity(VideoLayout.VGravity.centerCrop)
        videoLayout.setIsLoop(true)
        videoLayout.setSound(true)
        videoLayout.setPathOrUrl("loginvideotype3.mp4") // could be any video url
        frameLayout.addView(videoLayout)
```

```xml    
    <egolabsapps.basicodemine.videolayout.VideoLayout
            android:id="@+id/videoLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_gravity="center"
            app:sound="true"
            app:video_gravity="centerCrop"
            app:path_or_url="loginvideotype3.mp4"
            app:loop="true"/>
```

###### You should put your mp4 files into assets folder, If you use URL you should declare

```xml
   <uses-permission android:name="android.permission.INTERNET"/>
```

## Implementation Gradle

###### Add it in your root build.gradle at the end of repositories

```groovy
    repositories {
        maven { url 'https://jitpack.io' }
    }
```

###### Add the dependency

```groovy
    dependencies {
	    implementation 'com.github.AsynctaskCoffee:VideoLayout:1.3'
	}
```

## Implementation Maven

###### Add the JitPack repository to your build file 

```groovy
    <repositories>
	    <repository>
	        <id>jitpack.io</id>
	        <url>https://jitpack.io</url>
	    </repository>
    </repositories>
```

###### Add the dependency

```groovy
    <dependency>
        <groupId>com.github.AsynctaskCoffee</groupId>
        <artifactId>VideoLayout</artifactId>
        <version>1.3</version>
    </dependency>
```

### Updates

> 14.07.2020 - AndroidX migration und code refactor

> 21.09.2020 - Sound support and new preview design implementation added

> 02.12.2020 - Fix

## License

```
   Copyright 2020 Egemen ÖZOGUL

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
