import requests

pixela_endpoint = 'https://pixe.la/v1/users'
TOKEN = 'abcdefgh'
USERNAME = 'seongjae'
GRAPH_ID = 'graph1'

pixel_endpoint = f'{pixela_endpoint}/{USERNAME}/graphs/{GRAPH_ID}/20250103'
headers = {
    'X-USER-TOKEN': TOKEN
}
response = requests.delete(url=pixel_endpoint, headers=headers)
print(response.status_code)
print(response.json())