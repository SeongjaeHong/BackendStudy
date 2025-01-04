import requests

pixela_endpoint = 'https://pixe.la/v1/users'
TOKEN = 'abcdefgh'
USERNAME = 'seongjae'
user_params = {
    'token': TOKEN,
    'username': USERNAME,
    'agreeTermsOfService': 'yes',
    'notMinor': 'yes'
}

# response = requests.post(url=pixela_endpoint, json=user_params)
# print(response.text)

graph_endpoint = f'{pixela_endpoint}/{USERNAME}/graphs'
graph_params = {
    'id': 'graph1',
    'name': 'Cycling Graph',
    'unit': 'km',
    'type': 'float',
    'color': 'ajisai'
}
headers = {
    'X-USER-TOKEN': TOKEN
}
response = requests.post(url=graph_endpoint, json=graph_params, headers=headers)
print(response.json())