package com.example.kotlinflow

/*

    Kotlin Flow & Channels

    Kotlin has asynchronous stream support using Channels and Flows.

    1. Channels (Send & Receive)
    2. Flow (Emit & Collect)

    * Channels are hot. which means that the producer will keep on producing the data irrespective of the
    presence of the consumer.

    * Flows are cold. which means that the producer will only produce the data if there is a corresponding
    consumer to consume the produced data.

## Why Cold streams are preferred above Hot streams ?

    R:1 Resource Wastage :- as we know in case of hot stream, the producer will produce data irrespective of
    the presence of consumer or not. So, if there is no consumer than the data produced will be waste.

    R:2 Manual Close :- In case of hot stream, we need to manually close them but in case of cold stream
    the streams are closed automatically.

    R:3 Independent Flow :- Suppose if there are multiple consumer on the same producer and one consumer start
    consuming the data after some amount of time than the another consumer. So in case of flows, both the
    consumers will get the complete data as they are independent to each other.
    But in case of channel, if one channel starts consuming late as compared to the another consumer, than the
    late consumer will not get the data that has been produced before its consumption starts.

    *The kotlin flows cancel themselves when there is no consumer. So, if you want to cancel flow, cancel the
    consumer by stopping the coroutine in which it is consuming.
 */