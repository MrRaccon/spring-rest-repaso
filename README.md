# SPRING REPASO

Recuerda que esta aplicacion esta usando un REDIS que instalaste en tu maquina, si lo vuelves a bajar en el futuro necesitas el REDIS para manejar el cache que actualmente tienes

#Para redis : &nbsp;
&nbsp; cd /redis-6.2.2
&nbsp; src/redis-server

#Para la parte de Kafka &nbsp;
&nbsp;
#inicia ZooKeeper &nbsp;
bin/zookeeper-server-start.sh config/zookeeper.properties
&nbsp;
#inicia kafka &nbsp;
bin/kafka-server-start.sh config/server.properties
&nbsp;
#crear topic &nbsp;
bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic devs4j-topic --partitions 5 -- replication-factor 1
&nbsp;
#listar topics &nbsp;
bin/kafka-topics.sh --list--bootstrap-server localhost:9092
&nbsp;
#Describir topic &nbsp;
bin/kafka-topics.sh --describe --topic devs4j-topic --bootstrap-server localhost:9092
&nbsp;
#Crear un productor(envia mensajes a kafka) &nbsp;
bin/kafka-console-producer.sh --topic devs4j-topic --bootstrap-server localhost:9092
&nbsp;
#Crear un consumer &nbsp;
bin/kafka-console-consumer.sh --topic devs4j-topic --from-beginning -- bootstrap-server localhost:9092
