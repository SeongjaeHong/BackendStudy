import requests

APP_ID = '46638a7e'
APP_KEY = 'deb81d224d6a824738663283b0a669e9'

def main():
    host_url = 'https://trackapi.nutritionix.com'
    nlp_endpoint = 'v2/natural/exercise'
    nlp_url = f'{host_url}/{nlp_endpoint}'
    headers = {
        'x-app-id': APP_ID,
        'x-app-key': APP_KEY
    }
    body = {
        'query': 'I swam for 1 hour.'
    }
    response = requests.post(nlp_url, headers=headers, json=body)
    print(response.status_code)
    print(response.json())

if __name__ == '__main__':
    main()