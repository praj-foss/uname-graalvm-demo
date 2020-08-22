uname-graalvm-demo
==================
A simple demo of [uname](https://en.wikipedia.org/wiki/Uname)
system call from Java using [GraalVM](https://www.graalvm.org/).
It fetches kernel information similar to the `uname` command
found in Unix-likes.

## Building
You'll need JDK 11 or higher installed. Use the following command to build:
```shell script
./gradlew nativeImage
```
After compilation is done, run it using:
```
./build/graal/uname-graal
```

## Output
You can see most of the `uname -a` information in the output,
although it's not in a single line:
```
$ ./build/graal/uname-graal
Kernel name: Linux
Node name: praj-aspire
Kernel release: 5.7.10-zen1-1-zen
Kernel version: #1 ZEN SMP PREEMPT Wed, 22 Jul 2020 20:13:40 +0000
Machine: x86_64
```
