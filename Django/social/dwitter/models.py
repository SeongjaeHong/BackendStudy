from django.db import models
from django.db.models.signals import post_save
from django.contrib.auth.models import User
from django.dispatch import receiver


class Profile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    follows = models.ManyToManyField(
        'self',
        related_name='followed_by',
        symmetrical=False,
        blank=True
    )
    
    def __str__(self):
        return self.user.username
    
@receiver(post_save, sender=User)
def auto_create_profile(sender, instance, created, **kwargs):
    if created:
        create_profile(instance)
        
def create_profile(user):
    profile = Profile.objects.create(user=user)
    profile.save()
    
class Dweet(models.Model):
    user = models.ForeignKey(
        User, related_name='dweets', on_delete=models.DO_NOTHING
    )
    body = models.CharField(max_length=140)
    created_at = models.DateTimeField(auto_now_add=True)
    
    def __str__(self):
        if len(self.body) > 30:
            msg = self.body[:30] + '...'
        else:
            msg = self.body
            
        time = '{}/{:02d}/{:02d} {:02d}:{:02d}'.format(self.created_at.year, self.created_at.month, self.created_at.day, self.created_at.hour, self.created_at.minute)
        return f'{self.user}, ({time}), {msg}'