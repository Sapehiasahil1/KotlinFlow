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

    #Methods in Kotlin Flows
    * These are used to run the manual code that user want to execute during the flow emit and collect process.
    1. onStart -> called when flow is going to start
    2. onCompletion -> called when flow is completed
    3. onEach -> called when flow is going to emit in each iteration.

    #Operators in Kotlin Flows
    1. Terminal Operator -> They have the suspend keyword ahead of the fun keyword.
    eg: collect, first, toList

    2. Non-terminal Operator
    eg: map, filter, buffer
    * For starting the flow, we require a Terminal Operator.
    * If the producer is producing at more speed than consumer, then we add buffer in the producer.

    #Flow Context
    * Generally where the flow is being collected, on the same thread the flow is being emitted also.
    But if we want, that collection should take place on different thread and emission should take place on
    different thread. Then we can use
     "" Flow On""
    * We can also use multiple flowOn s.

    #Exception Handling
    * In order to handle the exception in the flow, we can use .catch operator.

 */