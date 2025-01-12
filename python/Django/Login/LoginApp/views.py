from django.shortcuts import redirect, render
from django.contrib import messages
from .forms import CreateUserForm, LoginForm

# Authentication models and functions
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required


def homepage(request):
    return render(request, "LoginApp/index.html")


def register(request):
    form = CreateUserForm()

    if request.method == "POST":
        form = CreateUserForm(request.POST)

        if form.is_valid():
            form.save()
            return redirect("LoginApp:user-login")
        else:
            messages.error(request, "Not valid.")
            print("\nNot valid\n")

    context = {"registerform": form}
    return render(request, "LoginApp/register.html", context=context)


def user_login(request):

    if request.method == "POST":
        form = LoginForm(request, data=request.POST)
        if form.is_valid():
            username = request.POST.get("username")
            password = request.POST.get("password")

            user = authenticate(request, username=username, password=password)
            if user is not None:
                login(request, user)
                return redirect("LoginApp:dashboard")
    form = LoginForm()
    context = {"loginform": form}
    return render(request, "LoginApp/user-login.html", context)


def user_logout(request):
    logout(request)
    return redirect("LoginApp:user-login")


@login_required(login_url="LoginApp:user-login")
def dashboard(request):
    return render(request, "LoginApp/dashboard.html")
