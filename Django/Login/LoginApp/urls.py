from django.urls import path
from . import views

app_name = "LoginApp"
urlpatterns = [
    path("", views.homepage, name="hompage"),
    path("register", views.register, name="register"),
    path("my-login", views.my_login, name="my-login"),
    path("dashboard", views.dashboard, name="dashboard"),
]
