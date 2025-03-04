Building Xenomai + Dovetail for BBB
===================================

- Add the Xenomai layer

$ bitbake-layers add-layer <path>/meta-xenomai

- Add the following lines to local.conf

# use Xenomai kernel  (from meta-xenomai)
PREFERRED_PROVIDER_virtual/kernel = "linux-xenomai-bbb"
IMAGE_INSTALL:append = " xenomai rt-tests"


- Build the image

$ bitbake xenomai-test-image

- Test "latency" on the target

root@beaglebone-yocto:~# latency
== Sampling period: 1000 us                                                
== Test mode: periodic user-mode task
== All results in microseconds                                           
warming up...                                                  
RTT|  00:00:01  (periodic user-mode task, 1000 us period, priority 99)
RTH|----lat min|----lat avg|----lat max|-overrun|---msw|---lat best|--lat worst 
RTD|      4.167|      4.918|     13.125|       0|     0|      4.167|     13.125 
RTD|      4.167|      5.037|     15.126|       0|     0|      4.167|     15.126 
RTD|      4.126|      5.200|     11.292|       0|     0|      4.126|     15.126 
...
