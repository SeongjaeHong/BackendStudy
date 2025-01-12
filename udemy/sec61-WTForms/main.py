from flask import Flask, render_template, request, redirect
from flask_wtf import FlaskForm
from wtforms import EmailField, PasswordField, SubmitField
from wtforms.validators import DataRequired, Email, ValidationError

app = Flask(__name__)
app.secret_key = 'abc'


def validate_password(form, pwd):
    if len(pwd.data) < 8:
        raise ValidationError('Password must be over 8 letters')

class MyForm(FlaskForm):
    email = EmailField('Email', validators=[DataRequired(), Email()])
    password = PasswordField('Password', validators=[DataRequired(), validate_password])
    submit = SubmitField('Log in')


@app.route("/")
def home():
    return render_template('index.html')

@app.route("/login", methods=['GET', 'POST'])
def login():
    myform = MyForm()
    if request.method == 'POST':
        data = request.form
        print(myform.validate_on_submit())
        print(data)
        return redirect(request.url)
    return render_template('login.html', form=myform)


if __name__ == '__main__':
    app.run(debug=True, port=1234)