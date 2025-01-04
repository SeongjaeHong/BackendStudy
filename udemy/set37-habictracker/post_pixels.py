import requests

pixela_endpoint = 'https://pixe.la/v1/users'
TOKEN = 'abcdefgh'
USERNAME = 'seongjae'
GRAPH_ID = 'graph1'

pixel_endpoint = f'{pixela_endpoint}/{USERNAME}/graphs/{GRAPH_ID}'
pixel_params = {
    'date': '20241123',
    'quantity': '8'
}
headers = {
    'X-USER-TOKEN': TOKEN
}
response = requests.post(url=pixel_endpoint, json=pixel_params, headers=headers)
print(response.status_code)
print(response.json())