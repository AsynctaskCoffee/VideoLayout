# VideoLayout :chicken:

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ea90e5f54edc468eb5e6246f9fc806ed)](https://app.codacy.com/app/AsynctaskCoffee/VideoLayout?utm_source=github.com&utm_medium=referral&utm_content=AsynctaskCoffee/VideoLayout&utm_campaign=Badge_Grade_Dashboard) [![License](https://img.shields.io/badge/License-Apache%202.0-yellowgreen.svg)](https://opensource.org/licenses/Apache-2.0) ![version](https://img.shields.io/badge/version-1.3-blue) [![Gitter](https://badges.gitter.im/VideoLayout/community.svg)](https://gitter.im/VideoLayout/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

![Preview](/previews/git.gif)

> 더욱 세련된 배경 디자인

> 성능 친화적이고 확장 가능

## 이 프로젝트가 존재하는 이유
Android의 일반적인 videoview는 성능 측면에서 매우 비쌉니다. 또한 비디오 해상도 비율과 APK 크기로 인해 대부분의 개발자는 프로젝트에 비디오를 포함하고 싶지 않습니다. 이 라이브러리를 통해 개발자는 몇 줄의 코드로 성능 친화적 인 비디오 배경을 갖게됩니다.
## 특징 및 사용법

### 간편한 구현

```java
        frameLayout = findViewById(R.id.frameLayout)
        videoLayout = VideoLayout(this)
        videoLayout.setGravity(VideoLayout.VGravity.centerCrop)
        videoLayout.setIsLoop(true)
        videoLayout.setSound(true)
        videoLayout.setPathOrUrl("loginvideotype3.mp4") // 모든 동영상 URL이 될 수 있습니다.
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

###### mp4 파일을 assets 폴더에 넣어야하며, URL을 사용하는 경우 인터넷 권한을 선언해야합니다.

```xml
   <uses-permission android:name="android.permission.INTERNET"/>
```

## 구현 Gradle

###### 저장소 끝의 루트 build.gradle에 추가하십시오.

```groovy
    repositories {
        maven { url 'https://jitpack.io' }
    }
```

###### 종속성 추가

```groovy
    dependencies {
	    implementation 'com.github.AsynctaskCoffee:VideoLayout:1.3'
	}
```

## 구현 Maven

###### 빌드 파일에 JitPack 저장소 추가

```groovy
    <repositories>
	    <repository>
	        <id>jitpack.io</id>
	        <url>https://jitpack.io</url>
	    </repository>
    </repositories>
```

###### 종속성 추가

```groovy
    <dependency>
        <groupId>com.github.AsynctaskCoffee</groupId>
        <artifactId>VideoLayout</artifactId>
        <version>1.3</version>
    </dependency>
```

### 업데이트

> 14.07.2020 - AndroidX 마이그레이션 및 코드 리팩터링

> 21.09.2020 - 사운드 지원 및 새로운 미리보기 디자인 구현 추가

> 02.12.2020 - 수정

## 특허

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
