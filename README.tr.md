# VideoLayout :chicken:

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ea90e5f54edc468eb5e6246f9fc806ed)](https://app.codacy.com/app/AsynctaskCoffee/VideoLayout?utm_source=github.com&utm_medium=referral&utm_content=AsynctaskCoffee/VideoLayout&utm_campaign=Badge_Grade_Dashboard) [![License](https://img.shields.io/badge/License-Apache%202.0-yellowgreen.svg)](https://opensource.org/licenses/Apache-2.0) ![version](https://img.shields.io/badge/version-1.3-blue) [![Gitter](https://badges.gitter.im/VideoLayout/community.svg)](https://gitter.im/VideoLayout/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

![Preview](/previews/git.gif)

> Şık arka plan tasarımları

> Performans dostu ve ölçeklenebilir

## Bu proje neden var ?
Android'deki normal VideoView, performans açısından çok maliyetlidir. Ayrıca, video çözünürlük oranı ve apk boyutu nedeniyle, çoğu geliştirici projeye harici olarak video eklemek istemiyor. Bu kütüphane ile uygulama geliştiriciler, birkaç satır kod içeren performans dostu bir video arka planına sahip olacaklar.
## Özellikler ve Kullanım
### Uygulamalarınıza kolaylıkla ekleyebilirsiniz

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

###### Mp4 dosyalarınızı assets klasörüne koymalısınız, URL kullanıyorsanız manifest içerisinde INTERNET iznini tanımlamayı unutmayın.

```xml
   <uses-permission android:name="android.permission.INTERNET"/>
```

## Gradle tanımlanması

###### root->gradle->repository katmanına eklemeniz gerekenler

```groovy
    repositories {
        maven { url 'https://jitpack.io' }
    }
```

###### app->gradle->dependencies katmanına eklemeniz gerekenler

```groovy
    dependencies {
	    implementation 'com.github.AsynctaskCoffee:VideoLayout:1.3'
	}
```

## Maven tanımlanması

###### JitPack repository tanımlamasını build dosyanıza ekleyin 

```groovy
    <repositories>
	    <repository>
	        <id>jitpack.io</id>
	        <url>https://jitpack.io</url>
	    </repository>
    </repositories>
```

###### VideoLayout dependency kimliklerini tanımlayın

```groovy
    <dependency>
        <groupId>com.github.AsynctaskCoffee</groupId>
        <artifactId>VideoLayout</artifactId>
        <version>1.3</version>
    </dependency>
```

### Güncellemeler

> 14.07.2020 - AndroidX'e geçiş ve kodsal düzenlemeler

> 21.09.2020 - Ses desteği ve demo uygulamasının görsel değişiklikleri

> 02.12.2020 - Fix

## Lisans

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
