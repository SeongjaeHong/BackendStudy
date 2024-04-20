from django.shortcuts import render
from .models import Profile, create_profile

def dashboard(request):
    return render(request, 'base.html')

def profile_list(request):
    profiles = Profile.objects.exclude(user=request.user)
    return render(request, "dwitter/profile_list.html", {"profiles": profiles})

def profile(request, pk):
    if not hasattr(request.user, 'profile'):
        create_profile(request.user)
        
    profile = Profile.objects.get(pk=pk)
    if request.method == 'POST':
        cur_user_profile = request.user.profile
        data = request.POST
        action = data.get('follow')
        if action == 'follow':
            cur_user_profile.follows.add(profile)
        elif action == 'unfollow':
            cur_user_profile.follows.remove(profile)
        cur_user_profile.save()
    return render(request, 'dwitter/profile.html', {'profile': profile})