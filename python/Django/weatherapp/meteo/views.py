from datetime import datetime
from django.shortcuts import render
import geocoder
import requests

from .models import Worldcities

def temp_somewhere(request):
    random_item = Worldcities.objects.all().order_by('?').first()
    city = random_item.city
    location = [str(random_item.lat), str(random_item.lng)]
    temp = get_temp(location)
    context = {
        'city': city,
        'temp': temp
    }
    return render(request, 'meteo/index.html', context)

def temp_here(request):
    location = geocoder.ip('me').latlng
    temp = get_temp(location)
    context = {
        'city': 'Your location',
        'temp': temp
    }
    return render(request, 'meteo/index.html', context)

def get_temp(location):
    endpoint = 'https://api.open-meteo.com/v1/forecast'
    api_request = f'{endpoint}?latitude={location[0]}&longitude={location[1]}&current=temperature_2m&hourly=temperature_2m'
    now = datetime.now()
    hour = now.hour
    meteo_data = requests.get(api_request).json()['hourly']['temperature_2m'][hour]
    return meteo_data
