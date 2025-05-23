from django.shortcuts import redirect, render

from .forms import DweetForm
from .models import Dweet, Profile, create_profile


def profile_list(request):
    profiles = Profile.objects.exclude(user=request.user)
    return render(request, "dwitter/profile_list.html", {"profiles": profiles})


def profile(request, pk):
    if not hasattr(request.user, "profile"):
        create_profile(request.user)

    profile = Profile.objects.get(pk=pk)
    if request.method == "POST":
        cur_user_profile = request.user.profile
        data = request.POST
        action = data.get("follow")
        if action == "follow":
            cur_user_profile.follows.add(profile)
        elif action == "unfollow":
            cur_user_profile.follows.remove(profile)
        cur_user_profile.save()
    return render(request, "dwitter/profile.html", {"profile": profile})


def dashboard(request):
    form = DweetForm(request.POST or None)
    if request.method == "POST":
        if form.is_valid():
            dweet = form.save(commit=False)
            dweet.user = request.user
            dweet.save()
            return redirect("dwitter:dashboard")
    followed_dweets = Dweet.objects.filter(
        user__profile__in=request.user.profile.follows.all()
    ).order_by("-created_at")
    return render(
        request, "dwitter/dashboard.html", {"form": form, "dweets": followed_dweets}
    )
