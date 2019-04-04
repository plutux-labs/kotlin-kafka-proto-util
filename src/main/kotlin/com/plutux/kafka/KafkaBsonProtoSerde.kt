package com.plutux.kafka

import com.google.protobuf.Message
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serdes

class KafkaBsonProtoSerde<T : Message>(clazz: Class<T>) :
    Serde<T> by Serdes.serdeFrom(KafkaBsonProtoSerializer(clazz), KafkaBsonProtoDeserializer(clazz))