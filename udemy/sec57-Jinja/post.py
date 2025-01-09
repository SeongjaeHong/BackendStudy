import requests
from flask import render_template

class Post:
    url = f'https://api.npoint.io/c790b4d5cab58020d391/'
    response = requests.get(url)
    posts = response.json()

    def get_posts(self):
        return render_template('index.html',
                               posts=self.posts)

    def get_post(self, post_id):
        return render_template('post.html',
                               post=self.posts[post_id])