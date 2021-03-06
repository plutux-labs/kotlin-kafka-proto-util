package com.plutux.kafka

import com.google.protobuf.Message
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serdes

class KafkaProtoSerde<T : Message>(clazz: Class<T>) :
    Serde<T> by Serdes.serdeFrom(KafkaProtoSerializer<T>(), KafkaProtoDeserializer(clazz))