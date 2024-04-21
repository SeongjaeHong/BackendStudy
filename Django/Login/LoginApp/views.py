from django.shortcuts import redirect, render
from .forms import CreateUserForm


def homepage(request):
    return render(request, "index.html")


def register(request):
    form = CreateUserForm()

    if request.method == "POST":
        form = CreateUserForm(request.POST)

        if form.is_valid():
            form.save()
            return redirect("LoginApp:my-login")

    context = {"registerform": form}
    return render(request, "register.html", context=context)


def my_loing(request):
    return render(request, "my-login.html")


def dashboard(request):
    return render(request, "dashboard.html")
