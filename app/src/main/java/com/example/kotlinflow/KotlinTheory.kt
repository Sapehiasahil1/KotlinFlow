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
 */