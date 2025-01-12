from django.urls import path
from . import views

app_name = "LoginApp"
urlpatterns = [
    path("", views.homepage, name="hompage"),
    path("register", views.register, name="register"),
    path("login", views.user_login, name="user-login"),
    path("dashboard", views.dashboard, name="dashboard"),
    path("logout", views.user_logout, name="user-logout"),
]
