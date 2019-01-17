package com.plutux.kafka

import com.google.protobuf.Message
import org.apache.kafka.common.serialization.Deserializer
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serializer

class KafkaProtoSerde<T : Message>(clazz: Class<T>) : Serde<T> {
    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {}

    private val deserializer = KafkaProtoDeserializer(clazz)
    override fun deserializer(): Deserializer<T> = deserializer

    override fun close() {}

    private val serializer = KafkaProtoSerializer<T>()
    override fun serializer(): Serializer<T> = serializer
}