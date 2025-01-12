import requests
from datetime import datetime as dt

import base64
from email.message import EmailMessage

from googleapiclient.discovery import build
from google.oauth2.credentials import Credentials

def gmail_send_message(content, receiver, subject, token):
    """Create and send an email message
    Print the returned  message id
    Returns: Message object, including message id

    Load pre-authorized user credentials from the environment.
    TODO(developer) - See https://developers.google.com/identity
    for guides on implementing OAuth2 for the application.
    """
    creds = Credentials.from_authorized_user_file(token,
                                                  ["https://www.googleapis.com/auth/gmail.send"])

    try:
        service = build("gmail", "v1", credentials=creds)
        message = EmailMessage()

        message.set_content(content)

        message["To"] = receiver
        message["From"] = "tjdwo0423@gmail.com"
        message["Subject"] = subject

        # encoded message
        encoded_message = base64.urlsafe_b64encode(message.as_bytes()).decode()

        create_message = {"raw": encoded_message}
        # pylint: disable=E1101
        send_message = (
            service.users().messages().send(userId="me", body=create_message).execute()
        )
        print(f'Message Id: {send_message["id"]}')
    # except HttpError as error:
    except TypeError as error:
        print(f"An error occurred: {error}")
        send_message = None
    return send_message

def is_iss_above_me(pos_me, pos_iss):
    chk_lat = abs(pos_me['lat'] - pos_iss['lat']) < 5
    chk_lon = abs(pos_me['lon'] - pos_iss['lon']) < 5

    if chk_lat & chk_lon:
        return True
    else:
        return False

def is_night_time(time_sunset, time_sunrise):
    cur_hour = dt.now().hour
    if cur_hour >= time_sunset or cur_hour <= time_sunrise:
        return True
    else:
        return False

def main():


    res_iss = requests.get('http://api.open-notify.org/iss-now.json')
    res_iss.raise_for_status()

    pos_iss = {'lat': float(res_iss.json()['iss_position']['latitude']),
               'lon': float(res_iss.json()['iss_position']['longitude'])}
    pos_me = {'lat': 36.5, 'lon':128.3}

    param = {'lat': pos_iss['lat'],
             'lng': pos_iss['lon'],
             'formatted': 0,
             'tzid': 'Asia/Seoul'}
    res_sun = requests.get('https://api.sunrise-sunset.org/json', params=param)
    res_sun.raise_for_status()
    data = res_sun.json()['results']
    time_sunset = dt.fromisoformat(data['sunset']).hour
    time_sunrise = dt.fromisoformat(data['solar_noon']).hour


    token='../../Practice/send_gmail/token.json'
    if is_night_time(time_sunset, time_sunrise) & is_iss_above_me(pos_me, pos_iss):
        content = 'ISS is on the Sky. Look up the Sky and find the ISS'
        gmail_send_message(content,
                           receiver='gtw04@naver.com',
                           subject='ISS Alarm',
                           token=token)
    else:
        content = 'ISS is not on the Sky. Hehe'
        gmail_send_message(content,
                           receiver='gtw04@naver.com',
                           subject='ISS Alarm',
                           token=token)


if __name__ == '__main__':
    main()