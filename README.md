# RxJava String

String, Byte and Character operators for [RxJava](https://github.com/ReactiveX/RxJava):

* Read bytes from an ```InputStream``` as a stream of byte arrays (```StringObservable.from```)
* Read text from a ```Reader``` as a stream of ```String``` (```StringObservable.from```)
* Convert between ```Observable<byte[]>``` and ```Observable<String>``` (```StringObservable.encode, decode```)
* Split text by regex (rechunks a stream of ```String```)  (```StringObservable.split```)
* Join text (```StringObservable.join, stringConcat```)
* Convert a ```String``` to an ```Observable<Character>``` or ```lift``` an ```Observable<String>```
  to an ```Observable<Character>``` (```CharacterObservable.from```)

## Master Build Status

<a href='https://travis-ci.org/ReactiveX/RxJavaString/builds'><img src='https://travis-ci.org/ReactiveX/RxJavaString.svg?branch=0.x'></a>

## Communication

- Google Group: [RxJava](http://groups.google.com/d/forum/rxjava)
- Twitter: [@RxJava](http://twitter.com/RxJava)
- [GitHub Issues](https://github.com/ReactiveX/RxJavaString/issues)


## Binaries

Binaries and dependency information for Maven, Ivy, Gradle and others can be found at [http://search.maven.org](http://search.maven.org/#search%7Cga%7C1%7Cio.reactivex.rxjava-string).

Example for Maven:

```xml
<dependency>
    <groupId>io.reactivex</groupId>
    <artifactId>rxjava-string</artifactId>
    <version>x.y.z</version>
</dependency>
```
and for Ivy:

```xml
<dependency org="io.reactivex" name="rxjava-string" rev="x.y.z" />
```

## Build

To build:

```
$ git clone git@github.com:ReactiveX/RxJavaString.git
$ cd RxJavaString/
$ ./gradlew build
```

## Bugs and Feedback

For bugs, questions and discussions please use the [Github Issues](https://github.com/ReactiveX/RxJavaString/issues).

 
## LICENSE

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

<http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
