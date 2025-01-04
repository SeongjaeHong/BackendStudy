import requests

pixela_endpoint = 'https://pixe.la/v1/users'
TOKEN = 'abcdefgh'
USERNAME = 'seongjae'
GRAPH_ID = 'graph1'

pixel_endpoint = f'{pixela_endpoint}/{USERNAME}/graphs/{GRAPH_ID}/20241123'
pixel_params = {
    'quantity': '7.23'
}
headers = {
    'X-USER-TOKEN': TOKEN
}
response = requests.put(url=pixel_endpoint, json=pixel_params, headers=headers)
print(response.status_code)
print(response.json())