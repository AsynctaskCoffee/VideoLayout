# VideoLayout :chicken:

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ea90e5f54edc468eb5e6246f9fc806ed)](https://app.codacy.com/app/AsynctaskCoffee/VideoLayout?utm_source=github.com&utm_medium=referral&utm_content=AsynctaskCoffee/VideoLayout&utm_campaign=Badge_Grade_Dashboard) [![License](https://img.shields.io/badge/License-Apache%202.0-yellowgreen.svg)](https://opensource.org/licenses/Apache-2.0) ![version](https://img.shields.io/badge/version-1.3-blue) [![Gitter](https://badges.gitter.im/VideoLayout/community.svg)](https://gitter.im/VideoLayout/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

![Preview](/previews/git.gif)

> 更时尚的背景设计

> 性能友好且可扩展

## 为什么这个项目存在
就性能而言，Android上的普通-videoview-成本很高。 另外，由于视频分辨率和apk大小，大多数开发人员都不希望在项目中包含视频。 有了这个库，开发人员将拥有几行代码的性能友好的视频背景。

## 功能与用法

### 易于实施 

```java
        frameLayout = findViewById(R.id.frameLayout)
        videoLayout = VideoLayout(this)
        videoLayout.setGravity(VideoLayout.VGravity.centerCrop)
        videoLayout.setIsLoop(true)
        videoLayout.setSound(true)
        videoLayout.setPathOrUrl("loginvideotype3.mp4") // 可以是任何视频网址
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

###### 您应该将mp4文件放入资产文件夹，如果使用URL，则应在AndroidManifest中声明互联网权限

```xml
   <uses-permission android:name="android.permission.INTERNET"/>
```

## 图书馆实施

###### 将其添加到存储库末尾的root build.gradle中

```groovy
    repositories {
        maven { url 'https://jitpack.io' }
    }
```

###### 添加依赖项

```groovy
    dependencies {
	    implementation 'com.github.AsynctaskCoffee:VideoLayout:1.3'
	}
```

## 实施Maven

###### 将JitPack存储库添加到您的构建文件中

```groovy
    <repositories>
	    <repository>
	        <id>jitpack.io</id>
	        <url>https://jitpack.io</url>
	    </repository>
    </repositories>
```

###### 添加依赖项

```groovy
    <dependency>
        <groupId>com.github.AsynctaskCoffee</groupId>
        <artifactId>VideoLayout</artifactId>
        <version>1.3</version>
    </dependency>
```

### 更新

> 14.07.2020 - AndroidX迁移和代码重构

> 21.09.2020 - 声音支持和新的预览设计实现已添加

> 02.12.2020 - 修正

## 执照

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
