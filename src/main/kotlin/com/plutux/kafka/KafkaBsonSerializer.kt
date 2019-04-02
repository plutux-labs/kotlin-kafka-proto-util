package com.plutux.kafka

import org.apache.kafka.common.serialization.Deserializer
import org.bson.BsonDocument
import org.bson.RawBsonDocument

class KafkaBsonDeserializer : Deserializer<BsonDocument> {
    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {}

    override fun deserialize(topic: String, data: ByteArray): BsonDocument {
        return RawBsonDocument(data, 0, data.size)
    }

    override fun close() {}
}

