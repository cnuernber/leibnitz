## Usage

source scripts/enable-jdk19

scripts/uberjar


## Example Output


```console

(base) chrisn@chrisn-lp2:~/dev/cnuernber/leibniz$ scripts/uberjar
Building uberjar
warning: using incubating module(s): jdk.incubator.vector
warning: using incubating module(s): jdk.incubator.vector
warning: using incubating module(s): jdk.incubator.vector
WARNING: Using incubator modules: jdk.incubator.vector
Running benchmark
WARNING: Using incubator modules: jdk.incubator.vector
Java original
"Elapsed time: 245.401501 msecs"
Java vectorized
"Elapsed time: 103.139107 msecs"
done
(base) chrisn@chrisn-lp2:~/dev/cnuernber/leibniz$
```
