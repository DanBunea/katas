# outside-in tdd bank kata in ClojureScript

This code is for the article on: [http://danbunea.blogspot.com/2018/08/outside-in-tdd-bank-kata-in.html](http://danbunea.blogspot.com/2018/08/outside-in-tdd-bank-kata-in.html)

## Setup

To get an interactive development environment run:

    lein figwheel dev test

and open your browser at [localhost:3449](http://localhost:3449/) and at [localhost:3449/test.html](http://localhost:3449/test.html) for the tests.
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

To clean all compiled files:

    lein clean

To create a production build run:

    lein do clean, cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL.

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
