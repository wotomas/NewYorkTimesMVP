# NewYorkTimesMVP
![Travis-ci](https://api.travis-ci.org/wotomas/NewYorkTimesMVP.svg)


New York Times Client MVP Experimental Project. 24 hour project created with use of MVP pattern, RxJava, and Testing

Punch Line
-------
 - Fetching data from a remote API and updating local data seamlessly
  - Use RxJava concatWith operator to manipulate data according to user's network status
  - Use RxJava retryWhen operator for optimized retry logic
 - Screen rotation handling
 - Decoupling logics from Android specific framework to make code testable and more maintainable
 - Memory Leak Identification


Dagger 2
-------
TODO


Testing
-------
Mockito and JUnit4 are used for tests.
TODO


Libraries
-------
    * testCompile "org.mockito:mockito-core:1.9.5"
    * testCompile 'junit:junit:4.12'
    * compile 'de.greenrobot:greendao:2.1.0'
    * compile 'com.google.code.gson:gson:2.8.0'
    * compile 'com.facebook.stetho:stetho:1.4.1'
    * compile 'com.squareup.retrofit2:retrofit:2.1.0'
    * compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    * compile 'io.reactivex:rxandroid:1.2.1'
    * compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
    * compile 'uk.co.chrisjenx:calligraphy:2.2.0'
    * compile 'com.squareup.picasso:picasso:2.5.2'
    * compile 'uk.co.chrisjenx:calligraphy:2.2.0'
    * compile 'com.squareup.okhttp3:logging-interceptor:3.3.0'
    * debugCompile 'com.squareup.leakcanary:leakcanary-android:1.5'
    * releaseCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.5'
    * testCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.5'
    
TODO
------
 - Decoupling more buisness logics
 - MultiMidea logic handling is dumb


License
-------
    Copyright 2017 Kim Jihyok

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
