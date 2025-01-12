from django.urls import include, path
from meteo import views

urlpatterns = [
    path('', views.temp_here, name='temp_here'),
    path('discover/', views.temp_somewhere, name='temp_somewhere')
]
