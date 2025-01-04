import requests
from datetime import datetime as dt

# Natural language API: Syndigo (https://docx.syndigo.com/)
APP_ID = '46638a7e'
APP_KEY = 'deb81d224d6a824738663283b0a669e9'

# Google sheet API: Sheety (https://sheety.co/)
def write_sheet(contents):
    post_endpoint = 'https://api.sheety.co/4639977aab3ba2b2468ad66fb0f4c313/myWorkouts/workouts'
    headers = {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer token'
    }
    for content in contents:
        data = {
            'workout': {
                'date': content['date'],
                'time': content['time'],
                'exercise': content['exercise'],
                'duration': content['duration'],
                'calories': content['calorie']
            }
        }

        response = requests.post(post_endpoint, json=data, headers=headers)
        response.raise_for_status()
        print(response.json())

def create_data(query):
    host_url = 'https://trackapi.nutritionix.com'
    nlp_endpoint = 'v2/natural/exercise'
    nlp_url = f'{host_url}/{nlp_endpoint}'
    headers = {
        'x-app-id': APP_ID,
        'x-app-key': APP_KEY
    }
    body = {
        'query': query,
        'weight_kg': 74.5,
        'height_cm': 180,
        'age': 29,
    }
    response = requests.post(nlp_url, headers=headers, json=body)
    response.raise_for_status()
    workouts = response.json()['exercises']

    results = []
    for workout in workouts:
        data = {
            'date': dt.now().strftime('%d/%m/%Y'),
            'time': dt.now().strftime('%H:%M:%S'),
            'exercise': workout['name'],
            'duration': workout['duration_min'],
            'calorie': workout['nf_calories'],
        }
        results.append(data)

    return results

def main():
    query = input('Tell me which exercises did you do today: ')
    contents = create_data(query)
    write_sheet(contents)

if __name__ == '__main__':
    main()