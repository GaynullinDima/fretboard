import requests
import json
import pprint

FILE_NAME = 'records.json'
USER  = 'token'
PASSWORD = 'my-secret'
SERVER_ENDPOINT = 'https://kinto.dev.mozaws.net'
PATH = '/v1/buckets/default/collections/tasks/records1'
USER_AGENT = 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:59.0) Gecko/20100101 Firefox/59.0'

def get_request(user, password, server_endpoint, path):
	r = requests.get(server_endpoint + path, auth=(user, password))

	print(r.status_code)
	print(r.text)


def post_request(user, password, server_endpoint, path, data_to_put):
	r = requests.post(server_endpoint + path, data = data_to_put, auth=(user, password))
	print(r.status_code)

def get_data_to_put(file_name):
	json_data=open(file_name).read()
	data = json.loads(json_data)
	pprint.pprint(data)
	return data

if __name__ == '__main__':
	data_to_put = get_data_to_put(file_name=FILE_NAME)
	print(type(data_to_put))
	post_request(user=USER, password=PASSWORD, server_endpoint=SERVER_ENDPOINT, path=PATH, data_to_put=data_to_put)
	get_request(user=USER, password=PASSWORD, server_endpoint=SERVER_ENDPOINT, path=PATH)
