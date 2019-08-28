import pika
import datetime
import json
import time
import random

message = {'fechaGeneracion': str(datetime.datetime.now()), 'IdDispositivo': 1, 'temperatura': 34, 'humedad': 50}

while True:
    message = {'fechaGeneracion': str(datetime.datetime.now()), 'IdDispositivo': 1, 'temperatura': random.randint(20, 50), 'humedad': random.randint(50, 100)}
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()
    channel.queue_declare(queue='dispositivo')
    channel.basic_publish(exchange='', routing_key='dispositivo',body=json.dumps(message))
    #print('enviando')
    time.sleep(10)

connection.close()
