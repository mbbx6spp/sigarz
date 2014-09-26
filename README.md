# Sigarz

[![Build Status](https://travis-ci.org/mbbx6spp/sigarz.svg?branch=master)](https://travis-ci.org/mbbx6spp/sigarz)

Sensible Scala bindings for the Sigar library.

## Status

This is very much a work in progress. I cannot stress this enough.

## Getting started

But to get started here is what I plan for the API looking/feeling like:

```scala
// Make sure you have SIGARZ_NATIVE_LIB_PATH environment variable set
val sigar = Sigarz.configure()

// Or you can pass in the path of the native library to configure itself
val sigar = Sigarz.configure(yourPath)

```

## Usage



## License

It will be released under the BSD 3-clause license. See LICENSE file.

Cheers.
[@SusanPotter](https://twitter.com/SusanPotter)
(Heckle me on Twitter for fun and profit?)
