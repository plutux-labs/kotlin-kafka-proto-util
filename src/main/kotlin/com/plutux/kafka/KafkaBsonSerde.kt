package com.plutux.kafka

import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serdes
import org.bson.BsonDocument

class KafkaBsonSerde : Serde<BsonDocument> by Serdes.serdeFrom(
    KafkaBsonSerializer(),
    KafkaBsonDeserializer()
)