from flask import Flask, render_template, request, redirect, flash
import requests

app = Flask(__name__)
app.secret_key = "super secret key"

response = requests.get('https://api.npoint.io/495f53c32892edd20f6e')
posts = response.json()

@app.route('/')
@app.route('/index.html')
def main():
    return render_template('index.html', posts=posts)

@app.route('/post.html', methods=['GET'])
def page_post():
    post_id = int(request.args['post_id']) - 1
    return render_template('post.html',
                           post=posts[post_id])

@app.route('/about.html')
def page_about():
    return render_template('about.html')

@app.route('/contact.html', methods=['GET', 'POST'])
def page_contact():
    if request.method == 'POST':
        req = request.form
        print('send_contact2:', req)
        print('email:', req.get('email'))
        print('url: ', request.url)
        flash('Warning message', 'warning')
        flash('Success message', 'success')
        return redirect(request.url)
    else:
        print('It\'s GET3')

    return render_template('contact.html')


if __name__ == '__main__':
    app.run(debug=True)