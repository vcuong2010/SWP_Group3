<%-- 
    Document   : profile
    Created on : Jan 17, 2024, 1:13:45 PM
    Author     : TGDD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Skill, java.util.ArrayList, model.User, java.text.SimpleDateFormat, model.Mentor, model.Mentee, model.MenteeStatistic, model.MentorStatistic" %>
<!doctype html>
<html lang="en" translate="no">
    <head>
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-60013507-9"></script>
        <meta charset="utf-8"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="-1"/>
        <meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no,maximum-scale=1"/>
        <meta name="theme-color" content="#000000"/>
        <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
        <meta name="description" content="Tham gia cộng đồng game thủ lớn nhất Việt Nam."/>
        <meta name="keywords" content="Tham gia cộng đồng game thủ lớn nhất Việt Nam."/>
        <meta name="google-site-verification" content="KYBoGz-bgnBnLpEUr3USGpSo1rVAvTS9LH44oCeunFw"/>
        <meta name="google" value="notranslate">
        <meta name="robots" content="INDEX,FOLLOW">
        <meta content="index,follow" name="googlebot">
        <meta name="copyright" content=" PlayerDuo 2022">
        <meta name="keywords" content="Playerduo, player duo, play dua, thuê gái chơi game">
        <meta name="description" content="PlayerDuo Cộng đồng game thủ lớn nhất Việt Nam, Cùng chơi với những game thủ chuyên nghiệp, hot streamer, hot girl và những người nổi tiếng.">
        <title>Statistic of requests</title>
        <meta content="index,follow" name="googlebot">
        <meta name="copyright" content=" PlayerDuo 2022">
        <meta name="robots" content="INDEX,FOLLOW">
        <meta property="og:type" content="article"/>
        <meta property="og:title" content="Game Community"/>
        <meta property="og:description" content="Tham gia cộng đồng game thủ lớn nhất Việt Nam."/>
        <meta property="og:image" content="https://playerduo-data.sgp1.cdn.digitaloceanspaces.com/production/images/thumbs.png"/>
        <meta property="og:video" content=""/>
        <title>Profile</title>
        <meta name="msapplication-TileColor" content="#ffffff"/>
        <meta name="msapplication-TileImage" content="/favicons/ms-icon-144x144.png"/>
        <meta name="theme-color" content="#ffffff"/>
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
        <link href="font-awesome/css/all.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400i,600,600i,700,700i&amp;subset=vietnamese" rel="stylesheet">
        <script defer="defer" src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
        <script defer="defer" src="https://apis.google.com/js/api.js"></script>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <title>Profile</title>
        <link href="css/8.97b85fe3.chunk.css" rel="stylesheet">
        <link href="css/main.3e229f12.chunk.css" rel="stylesheet">
        <style type="text/css">
            .react-skeleton-load {
                line-height: 1;
                display: inline-block;
                overflow: hidden;
                position: relative;
            }

            .react-skeleton-load.animated::before {
                content: '';
                position: absolute;
                height: 100%;
                width: 500px;
                top: 0;
                left: -500px;
                background-image: linear-gradient(90deg, rgba(255,255,255, 0), rgba(255,255,255, 0.6), rgba(255,255,255, 0));
                animation: progress 1.2s ease-in-out infinite
            }

            @keyframes progress {
                0% {
                    left: -500px
                }
                100% {
                    left: 100%
                }
            }</style>
        <style>
            .emoji-picker-react ul.skin-tones-list {
                padding: 0;
                margin: 0;
                list-style-type: none;
                position: absolute;
                top: 13px;
                right: 40px;
            }

            .emoji-picker-react ul.skin-tones-list li {
                background-color: currentColor;
                position: absolute;
                padding: 0;
                border-radius: 2px;
                overflow: hidden;
                transition: transform 0.3s ease;
            }

            .emoji-picker-react ul.skin-tones-list label {
                height: 10px;
                width: 10px;
                padding: 0;
                display: block;
                cursor: pointer;
            }

            .emoji-picker-react ul.skin-tones-list input {
                height: 0;
                width: 0;
                opacity: 0;
                visibility: hidden;
                display: none;
            }

            .emoji-picker-react ul.skin-tones-list li.tneutral {
                color: #ffd225;
            }
            .emoji-picker-react ul.skin-tones-list li.t1f3fb {
                color: #ffdfbd;
            }
            .emoji-picker-react ul.skin-tones-list li.t1f3fc {
                color: #e9c197;
            }
            .emoji-picker-react ul.skin-tones-list li.t1f3fd {
                color: #c88e62;
            }
            .emoji-picker-react ul.skin-tones-list li.t1f3fe {
                color: #a86637;
            }
            .emoji-picker-react ul.skin-tones-list li.t1f3ff {
                color: #60463a;
            }
        </style>
        <style>
            .emoji-picker-react img.emoji-img {
                height: 25px;
                width: 25px;
                margin: 5px;
            }

            .emoji-picker-react .native {
                height: 25px;
                width: 25px;
                margin: 5px;
                font-size: 25px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
        </style>
        <style>
            .emoji-picker-react .variation-wrapper {
                position: relative;
            }

            .emoji-picker-react .variation-list {
                background: #f4f4f4;
                border-bottom: 1px solid #efefef;
                position: absolute;
                top: 0;
                right: 0;
                left: 0;
                z-index: 10;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: space-evenly;
                list-style-type: none;
                transition: transform 0.1s;
                transform: translateY(-100%);
            }

            .emoji-picker-react .variation-list.visible {
                transform: translateY(0);
            }

            .emoji-picker-react .variation-list button {
                display: flex;
            }

            .emoji-picker-react .variation-list button {
                border-radius: 5px;
                transition: background 0.1s;
                background: none;
                padding: 0;
            }

            .emoji-picker-react .variation-list button:hover {
                background-color: #dbdbdb;
            }
        </style>
        <style>
            .emoji-picker-react .emoji-categories button {
                height: 40px;
                width: 20px;
                padding: 5px 0;
                background-repeat: no-repeat;
                background-size: 20px;
                background-position: 50% 50%;
                cursor: pointer;
                opacity: 0.5;
                transition: opacity 0.1s;
            }

            .emoji-picker-react .active-category-indicator-wrapper {
                position: relative;
                width: 100%;
            }
            .emoji-picker-react
            .active-category-indicator-wrapper
            .active-category-indicator {
                background: #99c2f1;
                height: 3px;
                width: 5px;
                position: absolute;
                bottom: 3px;
                border-radius: 5px;
                transition: 0.3s;
                width: 30px;
                left: -7px;
            }

            .emoji-picker-react .emoji-categories button.icn-activities {
                background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 64 64'%3E%3Cpath d='M32 0C14.355 0 0 14.355 0 32s14.355 32 32 32 32-14.355 32-32S49.645 0 32 0zm29.624 36.731l-3.885-6.439 2.681-7.88a29.867 29.867 0 0 1 1.204 14.319zm-7.558 15.567a.994.994 0 0 0-.408-.02L43.98 53.83a.993.993 0 0 0-.123-.345l-5.502-9.17 8.896-13.7h8.428a.992.992 0 0 0 .105.312l5.236 8.678a29.956 29.956 0 0 1-6.954 12.693zm-10.085 3.557l7.688-1.232a29.958 29.958 0 0 1-11.706 6.296l4.018-5.064zM12.65 9.1a29.858 29.858 0 0 1 18.628-7.082.982.982 0 0 0 .24.376l5.525 5.214-2.185 8.156-14.237 5.465c-.052-.042-.093-.094-.154-.126l-8.87-4.701L12.65 9.1zm25.736-2.976l-4.283-4.042a29.763 29.763 0 0 1 10.989 2.931l-6.706 1.111zM21.93 38.737l-.816-15.554L35.655 17.6l9.803 12.106-8.483 13.063-15.045-4.032zm37.375-19.141c-.031.054-.072.098-.093.159l-3.015 8.86h-9.048L36.882 15.937l2.113-7.887 8.27-1.371a.979.979 0 0 0 .453-.218 30.2 30.2 0 0 1 11.587 13.135zm-48.994-8.289l-.802 5.561-5.349 3.975a30.035 30.035 0 0 1 6.151-9.536zm-7.255 12.82c.044-.023.09-.037.131-.068l7.737-5.751 8.158 4.323.888 16.936c.002.025.013.048.016.073l-7.71 7.629c-.066.065-.105.145-.149.222L4.734 44.32c-.028-.012-.057-.009-.085-.018A29.822 29.822 0 0 1 2 32c0-2.725.372-5.362 1.056-7.873zm3.022 22.945l5.415 2.322 4.141 7.729a30.222 30.222 0 0 1-9.556-10.051zm12.759 11.879c-.019-.064-.025-.131-.058-.192l-5.317-9.924c.076-.043.155-.08.22-.145l8.027-7.942 14.507 3.888 5.927 9.879c.05.083.11.154.178.217l-5.449 6.867c-1.587.26-3.213.401-4.872.401-4.72 0-9.186-1.099-13.163-3.049z'/%3E%3C/svg%3E");
            }
            .emoji-picker-react .emoji-categories button.icn-animals_nature {
                background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 470 470'%3E%3Cpath d='M401.17 68.83C356.784 24.444 297.771 0 235 0S113.216 24.444 68.83 68.83 0 172.229 0 235s24.444 121.784 68.83 166.17S172.229 470 235 470s121.784-24.444 166.17-68.83S470 297.771 470 235s-24.444-121.784-68.83-166.17zM235 455c-121.309 0-220-98.691-220-220S113.691 15 235 15s220 98.691 220 220-98.691 220-220 220z'/%3E%3Cpath d='M382.5 173.979c3.532 0 6.735 1.824 8.568 4.879a7.499 7.499 0 0 0 12.864 0c1.833-3.055 5.036-4.879 8.568-4.879 4.143 0 7.5-3.357 7.5-7.5s-3.357-7.5-7.5-7.5c-5.461 0-10.724 1.829-15 5.039-4.276-3.21-9.539-5.039-15-5.039-4.143 0-7.5 3.357-7.5 7.5s3.357 7.5 7.5 7.5zM322.5 135.459c3.532 0 6.735 1.824 8.568 4.879a7.499 7.499 0 0 0 12.864 0c1.833-3.055 5.036-4.879 8.568-4.879 4.143 0 7.5-3.357 7.5-7.5s-3.357-7.5-7.5-7.5c-5.461 0-10.724 1.829-15 5.039-4.276-3.21-9.539-5.039-15-5.039-4.143 0-7.5 3.357-7.5 7.5s3.357 7.5 7.5 7.5zM117.5 173.979c3.532 0 6.735 1.824 8.568 4.879a7.499 7.499 0 0 0 12.864 0c1.833-3.055 5.036-4.879 8.568-4.879 4.143 0 7.5-3.357 7.5-7.5s-3.357-7.5-7.5-7.5c-5.461 0-10.724 1.829-15 5.039-4.276-3.21-9.539-5.039-15-5.039-4.143 0-7.5 3.357-7.5 7.5s3.357 7.5 7.5 7.5zM436.826 253.173a7.5 7.5 0 0 0-5.443-2.6c-12.664-.4-24.343-7.548-32.041-19.608a7.5 7.5 0 0 0-12.643-.001c-7.974 12.489-20.074 19.652-33.2 19.652-13.089 0-25.177-7.164-33.162-19.656a7.502 7.502 0 0 0-12.635-.004c-8 12.494-20.098 19.66-33.192 19.66-13.098 0-25.189-7.164-33.175-19.656a7.5 7.5 0 0 0-12.64.004c-7.974 12.489-20.069 19.652-33.187 19.652-13.098 0-25.19-7.164-33.176-19.656a7.502 7.502 0 0 0-12.635-.004c-8 12.494-20.098 19.66-33.191 19.66-13.099 0-25.19-7.164-33.175-19.655a7.5 7.5 0 0 0-12.64.004c-7.699 12.061-19.389 19.207-32.07 19.608a7.494 7.494 0 0 0-5.443 2.6 7.497 7.497 0 0 0-1.769 5.767c5.786 49.506 29.545 95.215 66.901 128.706C135.964 421.407 184.509 440 235 440c45.241 0 88.17-14.518 124.145-41.982a7.498 7.498 0 0 0 1.41-10.512 7.496 7.496 0 0 0-10.512-1.41C316.705 411.547 276.924 425 235 425c-93.882 0-173.276-68.424-187.68-160.366 11.265-2.217 21.561-8.215 29.707-17.284 10.49 11.584 24.673 18.267 39.476 18.267 14.808 0 29.002-6.691 39.505-18.291 10.493 11.6 24.685 18.291 39.498 18.291 14.828 0 29.022-6.689 39.511-18.284 10.493 11.595 24.682 18.284 39.491 18.284 14.808 0 29.002-6.691 39.505-18.291 10.493 11.6 24.679 18.291 39.485 18.291 14.826 0 29.018-6.681 39.505-18.264 8.14 9.065 18.422 15.061 29.671 17.278-6.044 38.177-24.008 74.246-51.068 102.269a7.5 7.5 0 1 0 10.791 10.419c31.08-32.185 51.038-74.226 56.198-118.38a7.495 7.495 0 0 0-1.769-5.766z'/%3E%3Cpath d='M289.513 310.616c-4.143 0-7.5 3.357-7.5 7.5s3.357 7.5 7.5 7.5h10c4.143 0 7.5-3.357 7.5-7.5s-3.357-7.5-7.5-7.5h-10zM358.49 280.616h-10c-4.143 0-7.5 3.357-7.5 7.5s3.357 7.5 7.5 7.5h10c4.143 0 7.5-3.357 7.5-7.5s-3.357-7.5-7.5-7.5zM111.503 280.616c-4.143 0-7.5 3.357-7.5 7.5s3.357 7.5 7.5 7.5h10c4.143 0 7.5-3.357 7.5-7.5s-3.357-7.5-7.5-7.5h-10zM235 191.25c30.327 0 55-24.673 55-55s-24.673-55-55-55-55 24.673-55 55 24.673 55 55 55zm0-95c22.056 0 40 17.944 40 40s-17.944 40-40 40-40-17.944-40-40 17.944-40 40-40z'/%3E%3C/svg%3E");
            }
            .emoji-picker-react .emoji-categories button.icn-flags {
                background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 502 502'%3E%3Cpath d='M428.484 73.516C381.076 26.108 318.044 0 251 0S120.924 26.108 73.516 73.516 0 183.956 0 251s26.108 130.076 73.516 177.484S183.956 502 251 502s130.076-26.108 177.484-73.516C475.892 381.076 502 318.044 502 251s-26.108-130.076-73.516-177.484zM283.829 39h58.868c58.354 25.338 104.274 74.079 125.849 134.317h-41.725a21.139 21.139 0 0 0-19.587 13.087 21.139 21.139 0 0 0 4.595 23.104l3.3 3.3c4.638 4.637 4.638 12.184 0 16.821a11.42 11.42 0 0 1-8.13 3.368 11.422 11.422 0 0 1-8.13-3.368l-7.969-7.969c-13.135-13.135-30.599-20.369-49.175-20.369h-6.397v-8.036c0-19.265-7.502-37.376-21.124-50.999l-9.952-9.952c-10.216-10.216-23.799-15.843-38.247-15.843h-19.931c-7.721 0-14.98 3.007-20.439 8.466l-5.17 5.169c-5.459 5.459-8.466 12.718-8.466 20.439a4.736 4.736 0 0 1-4.73 4.73h-8.66v-12.154c0-8.648 3.368-16.78 9.483-22.895l5.849-5.849c5.244-5.243 8.131-12.214 8.131-19.629V92.71c0-.394.32-.713.713-.713H320.5c12.407 0 22.5-10.093 22.5-22.5S332.907 47 320.5 47h-36.671c-2.206 0-4-1.794-4-4s1.794-4 4-4zm74.893 252.437l-5.452 5.484a155.066 155.066 0 0 0-22.913 29.41l-9.918 16.5-12.403 20.492a48.673 48.673 0 0 0-7.036 25.21v.615a.857.857 0 0 1-.856.856h-.004a8.78 8.78 0 0 1-6.247-2.586 8.776 8.776 0 0 1-2.589-6.25c0-12.58-4.899-24.407-13.794-33.303l-4.591-4.591c-6.947-6.947-10.773-16.183-10.773-26.007v-29.475c0-14.806-12.045-26.851-26.852-26.851H231.8c-8.349 0-15.142-6.792-15.142-15.142v-15.343c0-9.034 7.35-16.384 16.384-16.384h79.886l24.099 24.1c6.003 6.003 9.309 13.984 9.309 22.473v11.464c0 8.56 5.082 15.955 12.386 19.328zM20 251c0-9.444.583-18.752 1.69-27.902h30.619c10.153 0 19.698 3.954 26.876 11.133l8.781 8.78c7.527 7.527 17.534 11.672 28.179 11.672 5.65 0 10.962 2.2 14.957 6.195l.193.193c7.233 7.233 11.217 16.851 11.217 27.081v17.886c0 13.63-5.308 26.444-14.945 36.082l-19.15 19.15c-13.442 13.443-21.939 30.512-24.58 49.002C44.303 368.799 20 312.684 20 251zm231 231c-56.288 0-107.93-20.247-148.049-53.827v-5.423c0-17.881 6.963-34.693 19.607-47.337l19.15-19.15c13.415-13.416 20.803-31.252 20.803-50.224v-17.886c0-15.573-6.064-30.213-17.075-41.224l-.193-.192c-7.772-7.772-18.106-12.053-29.099-12.053a19.72 19.72 0 0 1-14.036-5.814l-8.781-8.781c-10.957-10.956-25.524-16.99-41.019-16.99h-27.3C47.126 98.635 140.047 20 251 20c7.743 0 15.396.39 22.946 1.138-8.316 3.774-14.117 12.151-14.117 21.862 0 13.234 10.766 24 24 24H320.5c1.378 0 2.5 1.122 2.5 2.5s-1.122 2.5-2.5 2.5h-97.713c-11.421 0-20.713 9.292-20.713 20.713v2.028a7.706 7.706 0 0 1-2.273 5.486l-5.85 5.85c-9.893 9.893-15.341 23.047-15.341 37.037v13.574c0 10.245 8.334 18.58 18.579 18.58h10.081c13.636 0 24.73-11.094 24.73-24.73 0-2.379.926-4.615 2.608-6.297l5.169-5.169c.203-.203.414-.393.632-.574.167.195.334.389.518.574l19.932 19.932c-3.833 3.911-3.813 10.186.068 14.068 1.953 1.953 4.512 2.929 7.071 2.929s5.119-.976 7.071-2.929l7-7c3.905-3.905 3.905-10.237 0-14.143l-15.45-15.45c8.875.156 17.197 3.677 23.489 9.97l9.953 9.952c9.844 9.844 15.266 22.934 15.266 36.856v.817H233.04c-20.062 0-36.384 16.322-36.384 36.384V245.8c0 19.377 15.765 35.142 35.142 35.142h3.493a6.86 6.86 0 0 1 6.852 6.851v29.475c0 15.167 5.906 29.425 16.63 40.15l4.591 4.591c5.118 5.118 7.937 11.923 7.937 19.161 0 7.705 3.001 14.948 8.451 20.396 5.446 5.443 12.685 8.44 20.384 8.44h.015C311.648 410 321 400.644 321 389.149v-.614a28.68 28.68 0 0 1 4.146-14.854l12.409-20.502a.226.226 0 0 1 .016-.026l9.928-16.517a135.064 135.064 0 0 1 19.955-25.613l11.147-11.213c4.428-4.455 5.731-11.08 3.319-16.879s-8.029-9.546-14.31-9.546a1.274 1.274 0 0 1-1.273-1.273v-11.464c0-13.832-5.386-26.835-15.167-36.616l-2.215-2.215c10.49 1.524 20.173 6.357 27.804 13.988l7.969 7.969c6.141 6.141 14.207 9.211 22.272 9.211s16.132-3.07 22.272-9.211c6.024-6.024 9.341-14.033 9.341-22.553 0-8.519-3.317-16.528-9.341-22.553l-3.3-3.3c-.198-.198-.567-.567-.26-1.308.307-.741.829-.741 1.109-.741h47.888C479.468 211.761 482 231.09 482 251c0 127.374-103.626 231-231 231z'/%3E%3Cpath d='M184 85c5.523 0 10-4.477 10-10V54.494c0-5.523-4.477-10-10-10s-10 4.477-10 10V75c0 5.523 4.477 10 10 10zM450.39 314.63c-5.176-1.93-10.935.702-12.863 5.877C408.652 397.961 333.692 450 251 450c-5.523 0-10 4.477-10 10s4.477 10 10 10c45.543 0 89.207-13.849 126.272-40.048 36.24-25.617 63.556-61.046 78.995-102.458 1.929-5.175-.702-10.934-5.877-12.864zM202.433 444.034a198.232 198.232 0 0 1-28.554-9.526c-5.092-2.144-10.954.249-13.096 5.339-2.142 5.09.249 10.954 5.339 13.096a218.202 218.202 0 0 0 31.445 10.491c.817.205 1.635.303 2.44.303 4.478 0 8.554-3.03 9.692-7.57 1.344-5.358-1.909-10.79-7.266-12.133z'/%3E%3C/svg%3E");
            }
            .emoji-picker-react .emoji-categories button.icn-food_drink {
                background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 512 512'%3E%3Cpath d='M337.502 61.244c-46.267-19.341-98.094-21.573-145.933-6.282-5.497 1.758-8.528 7.638-6.772 13.134 1.758 5.497 7.64 8.528 13.134 6.772 43.115-13.782 89.819-11.772 131.51 5.657 1.317.55 2.682.811 4.026.811 4.087 0 7.969-2.415 9.644-6.422 2.228-5.324-.284-11.445-5.609-13.67zM368.323 77.252a11.31 11.31 0 0 0-.375-.239c-4.925-3.009-11.356-1.458-14.364 3.467-2.984 4.884-1.483 11.249 3.346 14.29a10.398 10.398 0 0 0 5.587 1.626c3.377 0 6.69-1.633 8.704-4.654 3.201-4.801 1.904-11.289-2.898-14.49zM447.293 161.884c-4.081-4.08-10.698-4.08-14.778 0l-14.629 14.629c-4.08 4.081-4.08 10.698 0 14.778 2.04 2.041 4.715 3.06 7.388 3.06s5.349-1.02 7.389-3.06l14.629-14.629c4.081-4.081 4.081-10.698.001-14.778zM83.999 214.617c-4.081-4.08-10.698-4.08-14.778 0l-14.629 14.629c-4.08 4.081-4.08 10.698 0 14.778 2.04 2.041 4.715 3.06 7.388 3.06s5.349-1.02 7.39-3.06l14.629-14.629c4.081-4.082 4.081-10.698 0-14.778zM115.508 100.235c-4.081-4.08-10.698-4.08-14.778 0l-14.629 14.629c-4.08 4.081-4.08 10.698 0 14.778 2.04 2.041 4.715 3.06 7.388 3.06s5.348-1.02 7.39-3.06l14.629-14.629c4.081-4.081 4.081-10.698 0-14.778zM386.754 116.24h-20.688c-5.771 0-10.449 4.678-10.449 10.449s4.678 10.449 10.449 10.449h20.688c5.771 0 10.449-4.678 10.449-10.449s-4.678-10.449-10.449-10.449zM151.326 161.908l-14.618-14.618c-4.081-4.08-10.698-4.081-14.778 0s-4.08 10.698 0 14.778l14.618 14.618a10.413 10.413 0 0 0 7.388 3.06c2.674 0 5.349-1.02 7.39-3.06 4.081-4.081 4.081-10.698 0-14.778zM411.753 229.241l-14.618-14.617c-4.08-4.081-10.696-4.08-14.777 0s-4.08 10.697 0 14.777l14.618 14.617c2.041 2.041 4.715 3.06 7.388 3.06s5.348-1.021 7.388-3.06c4.083-4.08 4.082-10.696.001-14.777zM318.326 126.607l-14.617-14.617c-4.081-4.08-10.698-4.081-14.778 0s-4.08 10.698 0 14.778l14.617 14.617a10.414 10.414 0 0 0 7.388 3.061 10.42 10.42 0 0 0 7.39-3.061c4.08-4.081 4.08-10.698 0-14.778zM195.194 97.387c-3.904-4.25-10.515-4.528-14.763-.622l-15.22 13.989c-4.249 3.905-4.527 10.515-.622 14.763a10.42 10.42 0 0 0 7.696 3.378c2.528 0 5.063-.911 7.068-2.756l15.22-13.989c4.248-3.905 4.526-10.515.621-14.763zM256.153 145.241H255.865c-5.762 0-10.437 4.665-10.449 10.429-.011 5.771 4.658 10.457 10.429 10.469H256.134c5.762 0 10.438-4.664 10.449-10.429.01-5.771-4.659-10.458-10.43-10.469z'/%3E%3Cpath d='M437.019 74.981C388.668 26.628 324.38 0 256 0S123.332 26.628 74.981 74.981C26.629 123.333 0 187.62 0 256c0 68.38 26.628 132.668 74.981 181.019C123.333 485.371 187.62 512 256 512c68.38 0 132.668-26.628 181.019-74.981C485.371 388.667 512 324.38 512 256s-26.628-132.668-74.981-181.019zM256 491.102c-94.256 0-175.718-55.763-213.173-136.024 6.781-.56 13.126-3.458 18.112-8.35 5.756-5.647 9.057-13.495 9.057-21.531v-9.364c0-7.449 6.027-13.624 13.435-13.767 3.561-.079 6.976 1.322 9.617 3.913 2.77 2.718 4.359 6.484 4.359 10.332v23.709c0 18.754 15.222 34.302 33.932 34.66.221.004.442.006.664.006 8.973 0 17.47-3.499 23.989-9.895 6.751-6.623 10.623-15.826 10.623-25.25v-18.215c20.172 27.524 52.723 45.432 89.384 45.432 52.209 0 96.09-36.312 107.73-85.007a10.027 10.027 0 0 1 2.18 6.23v25.773c0 19.673 15.968 35.984 35.596 36.361.233.004.464.006.696.006 9.409 0 18.321-3.671 25.161-10.38 7.086-6.951 11.149-16.61 11.149-26.5v-24.856c0-2.83 1.17-5.6 3.21-7.602 1.927-1.889 4.429-2.882 6.986-2.854 5.403.104 9.8 4.612 9.8 10.05v2.721c0 14.409 10.071 26.69 23.526 30.04C453.848 418.996 363.189 491.102 256 491.102zm223.405-200.399v-2.721c0-16.741-13.591-30.624-30.297-30.944-8.235-.159-16.057 2.978-22.022 8.829-6.021 5.906-9.473 14.113-9.473 22.52v24.856c0 4.314-1.782 8.536-4.886 11.582-2.97 2.912-6.846 4.462-10.82 4.397-8.326-.16-15.099-7.098-15.099-15.468v-25.773c0-13.154-8.392-24.538-20.091-28.971.027-1.001.043-2.004.043-3.011 0-46.06-29.007-87.788-72.182-103.836-5.411-2.01-11.425.745-13.434 6.154-2.01 5.409.745 11.424 6.154 13.434 35.027 13.021 58.562 46.877 58.562 84.248 0 49.549-40.312 89.861-89.861 89.861-49.549 0-89.861-40.312-89.861-89.861 0-37.372 23.535-71.228 58.565-84.246 5.409-2.01 8.164-8.026 6.154-13.434s-8.022-8.165-13.434-6.154C174.248 168.21 145.239 209.938 145.239 256a110.22 110.22 0 0 0 7.085 38.971 30.145 30.145 0 0 0-6.607 18.808v25.763c0 3.848-1.589 7.614-4.359 10.332-2.642 2.591-6.036 3.973-9.617 3.913-7.407-.142-13.434-6.317-13.434-13.766v-23.709c0-9.424-3.872-18.627-10.623-25.251-6.681-6.554-15.435-10.081-24.652-9.889-18.71.358-33.932 15.907-33.932 34.66v9.364c0 2.461-1.019 4.871-2.794 6.613-1.668 1.635-3.808 2.506-6.034 2.47-3.374-.065-6.387-2.139-7.733-5.21-7.543-23.011-11.64-47.569-11.64-73.07C20.898 126.365 126.365 20.898 256 20.898S491.102 126.365 491.102 256c0 15.182-1.464 30.026-4.227 44.414-4.281-1.138-7.47-5.083-7.47-9.711z'/%3E%3C/svg%3E");
            }
            .emoji-picker-react .emoji-categories button.icn-objects {
                background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 58.153 58.153'%3E%3Cpath d='M40.076 29.153h-7.142a3.995 3.995 0 0 0-2.858-2.858V16.153a1 1 0 1 0-2 0v10.142c-1.72.447-3 1.999-3 3.858 0 2.206 1.794 4 4 4 1.858 0 3.411-1.28 3.858-3h7.142a1 1 0 1 0 0-2zm-11 3c-1.103 0-2-.897-2-2s.897-2 2-2 2 .897 2 2-.897 2-2 2z'/%3E%3Cpath d='M50.188 9.764l4.096 4.096a1 1 0 0 0 1.414 0c3.167-3.166 3.167-8.319 0-11.485s-8.319-3.166-11.485 0a.997.997 0 0 0 0 1.414l4.561 4.561-1.699 1.699c-4.78-4.284-11.089-6.896-17.998-6.896s-13.218 2.612-17.998 6.896l-1.7-1.699 4.561-4.561a.997.997 0 0 0 0-1.414c-3.166-3.166-8.318-3.166-11.485 0s-3.167 8.319 0 11.485a1 1 0 0 0 1.414 0l4.096-4.096 1.676 1.676c-4.679 4.857-7.565 11.453-7.565 18.713 0 9.898 5.357 18.564 13.321 23.265l-3.028 3.028a.999.999 0 1 0 1.414 1.414l3.45-3.45c3.578 1.754 7.597 2.743 11.843 2.743s8.265-.989 11.843-2.743l3.45 3.45a.997.997 0 0 0 1.414 0 .999.999 0 0 0 0-1.414l-3.028-3.028c7.964-4.701 13.321-13.367 13.321-23.265 0-7.26-2.886-13.856-7.565-18.713l1.677-1.676zm4.095-5.975c2.146 2.146 2.362 5.502.649 7.893L46.391 3.14a6.13 6.13 0 0 1 7.892.649zM3.22 11.681c-1.713-2.39-1.497-5.746.649-7.892s5.502-2.361 7.892-.649L3.22 11.681zm25.856 43.472c-13.785 0-25-11.215-25-25s11.215-25 25-25 25 11.215 25 25-11.214 25-25 25z'/%3E%3Cpath d='M29.076 10.032a1 1 0 0 0 1-1v-1a1 1 0 1 0-2 0v1a1 1 0 0 0 1 1zM29.076 50.032a1 1 0 0 0-1 1v1a1 1 0 1 0 2 0v-1a1 1 0 0 0-1-1zM50.076 31.032h1a1 1 0 1 0 0-2h-1a1 1 0 1 0 0 2zM8.076 29.032h-1a1 1 0 1 0 0 2h1a1 1 0 1 0 0-2zM43.926 13.768l-.707.707a.999.999 0 1 0 1.414 1.414l.707-.707a.999.999 0 1 0-1.414-1.414zM13.52 44.174l-.707.707a.999.999 0 1 0 1.414 1.414l.707-.707a.999.999 0 1 0-1.414-1.414zM44.633 44.174a.999.999 0 1 0-1.414 1.414l.707.707a.997.997 0 0 0 1.414 0 .999.999 0 0 0 0-1.414l-.707-.707zM14.227 13.768a.999.999 0 1 0-1.414 1.414l.707.707a.997.997 0 0 0 1.414 0 .999.999 0 0 0 0-1.414l-.707-.707z'/%3E%3C/svg%3E");
            }
            .emoji-picker-react .emoji-categories button.icn-smileys_people {
                background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 33 33'%3E%3Cpath d='M16.5 33C7.402 33 0 25.598 0 16.5S7.402 0 16.5 0 33 7.402 33 16.5 25.598 33 16.5 33zm0-32C7.953 1 1 7.953 1 16.5S7.953 32 16.5 32 32 25.047 32 16.5 25.047 1 16.5 1z'/%3E%3Cpath d='M16.5 33a16.38 16.38 0 0 1-9.549-3.06.5.5 0 1 1 .116-.876c4.146-1.535 4.815-2.781 4.815-5.169 0-.631-.142-.838-.398-1.214-.339-.494-.803-1.171-1.129-2.939-.048-.254-.089-.274-.316-.384-.606-.292-1.163-.712-1.309-2.628 0-.928.32-1.441.585-1.708-.058-.33-.153-.899-.242-1.519-.453-2.777-.473-6.178 3.433-7.759 3.404-1.38 6.121-.626 6.974.273.604.019 2.162.177 3.246 1.438 1.668 1.94 1.137 6.363.955 7.562.266.261.589.767.589 1.675-.146 1.954-.703 2.375-1.31 2.666-.228.11-.269.129-.316.384-.326 1.768-.789 2.445-1.128 2.939-.257.375-.398.583-.398 1.214 0 2.388.669 3.634 4.815 5.169a.498.498 0 0 1 .116.876A16.38 16.38 0 0 1 16.5 33zm-8.183-3.349C10.779 31.191 13.589 32 16.5 32s5.721-.809 8.183-2.349c-3.474-1.426-4.565-2.864-4.565-5.755 0-.941.278-1.348.573-1.779.304-.444.682-.996.971-2.556.139-.754.576-.964.865-1.103.311-.149.631-.303.744-1.803-.001-.764-.344-.972-.358-.98a.533.533 0 0 1-.264-.537c.248-1.329.656-5.474-.681-7.031-.913-1.062-2.352-1.091-2.626-1.08-.046-.004-.091-.005-.134-.016-.13-.033-.35-.146-.417-.262-.272-.466-2.641-1.403-5.91-.08-3.231 1.308-3.238 4.112-2.819 6.682.138.957.289 1.784.29 1.788a.5.5 0 0 1-.283.544c.003 0-.339.209-.339 1.008.112 1.461.433 1.616.743 1.765.289.139.727.349.866 1.103.288 1.56.666 2.112.97 2.556.296.431.574.838.574 1.779 0 2.894-1.091 4.332-4.566 5.757z'/%3E%3C/svg%3E");
            }
            .emoji-picker-react .emoji-categories button.icn-symbols {
                background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 490.4 490.4'%3E%3Cpath d='M229 381.2c4.4 4.4 10.1 6.8 16.3 6.8 6.2 0 12-2.4 16.3-6.8l96.3-96.3c15.7-15.7 24.4-36.6 24.4-58.7 0-22.2-8.6-43.1-24.3-58.8-15.7-15.7-36.6-24.3-58.7-24.3-20 0-38.9 7-54 19.9-15.1-13-34.1-20-54.1-20-22.2 0-43 8.6-58.7 24.3s-24.3 36.6-24.3 58.8 8.7 43 24.4 58.7l96.4 96.4zm-79.3-196.7c11.1-11.1 25.7-17.1 41.4-17.1s30.4 6.1 41.5 17.2l4 4c4.8 4.8 12.5 4.8 17.3 0l3.9-3.9c11.1-11.1 25.8-17.2 41.5-17.2 15.6 0 30.3 6.1 41.4 17.2 11.1 11.1 17.2 25.8 17.1 41.4 0 15.7-6.1 30.4-17.2 41.5l-95.3 95.3-95.5-95.5c-11.1-11.1-17.2-25.8-17.2-41.4 0-15.7 6.1-30.4 17.1-41.5z'/%3E%3Cpath d='M245.2 490.4c135.2 0 245.2-110 245.2-245.2S380.4 0 245.2 0 0 110 0 245.2s110 245.2 245.2 245.2zm0-465.9c121.7 0 220.7 99 220.7 220.7s-99 220.7-220.7 220.7-220.7-99-220.7-220.7 99-220.7 220.7-220.7z'/%3E%3C/svg%3E");
            }
            .emoji-picker-react .emoji-categories button.icn-travel_places {
                background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 470 470'%3E%3Cpath d='M401.17 68.83C356.784 24.444 297.771 0 235 0S113.216 24.444 68.83 68.83C24.444 113.215 0 172.229 0 235s24.444 121.785 68.83 166.17C113.216 445.556 172.229 470 235 470s121.784-24.444 166.17-68.83C445.556 356.785 470 297.771 470 235s-24.444-121.785-68.83-166.17zM235 455c-121.309 0-220-98.691-220-220S113.691 15 235 15s220 98.691 220 220-98.691 220-220 220z'/%3E%3Ccircle cx='235' cy='97.5' r='7.5'/%3E%3Cpath d='M437.56 242.365a7.501 7.501 0 0 0-5.467-2.365h-26.046v-22.5c0-4.142-3.357-7.5-7.5-7.5s-7.5 3.358-7.5 7.5V240H372.5v-52.5a7.5 7.5 0 0 0-3.525-6.36L292.5 133.343V97.5A7.5 7.5 0 0 0 285 90h-13.253C268.262 72.905 253.109 60 235 60s-33.262 12.905-36.747 30H185a7.5 7.5 0 0 0-7.5 7.5V130h-25v-22.5c0-4.142-3.357-7.5-7.5-7.5s-7.5 3.358-7.5 7.5V130H105a7.5 7.5 0 0 0-7.5 7.5v102.499l-59.593-.01a7.504 7.504 0 0 0-7.487 7.969c3.523 56.171 29.666 105.984 69.187 140.798.281.291.587.556.911.799 23.389 20.362 51.39 35.496 82.128 43.638.307.102.622.184.946.246A204.258 204.258 0 0 0 235 440c17.409 0 34.679-2.229 51.386-6.558a7.297 7.297 0 0 0 1.002-.262 203.842 203.842 0 0 0 50.574-20.966c30.222-17.629 55.631-42.86 73.479-72.965a7.5 7.5 0 0 0-12.902-7.65 189.49 189.49 0 0 1-26.039 34.299V255h51.438a188.457 188.457 0 0 1-12.616 50.728 7.499 7.499 0 0 0 4.156 9.758 7.498 7.498 0 0 0 9.758-4.157 203.511 203.511 0 0 0 14.342-63.359 7.499 7.499 0 0 0-2.018-5.605zM192.5 175h85v215h-85V175zm0-70H205a7.5 7.5 0 0 0 7.5-7.5c0-12.407 10.094-22.5 22.5-22.5s22.5 10.093 22.5 22.5a7.5 7.5 0 0 0 7.5 7.5h12.5v55h-85v-55zM46.059 254.99l51.441.009V307.5c0 4.142 3.357 7.5 7.5 7.5s7.5-3.358 7.5-7.5V145h65v245H175c-4.143 0-7.5 3.358-7.5 7.5s3.357 7.5 7.5 7.5h2.5v11.078c-24.056-7.668-46.091-20.018-65-35.997V337.5c0-4.142-3.357-7.5-7.5-7.5s-7.5 3.358-7.5 7.5v28.458c-28.127-29.492-46.937-68.033-51.441-110.968zM192.5 420.179V405h85v15.106A187.644 187.644 0 0 1 235 425a189.427 189.427 0 0 1-42.5-4.821zm100-4.235V405h2.5c4.143 0 7.5-3.358 7.5-7.5s-3.357-7.5-7.5-7.5h-2.5V151.032l65 40.625v188.307a191.989 191.989 0 0 1-65 35.98z'/%3E%3Cpath d='M325 320a7.5 7.5 0 0 0-7.5 7.5v10c0 4.142 3.357 7.5 7.5 7.5s7.5-3.358 7.5-7.5v-10a7.5 7.5 0 0 0-7.5-7.5zM325 280a7.5 7.5 0 0 0-7.5 7.5v10c0 4.142 3.357 7.5 7.5 7.5s7.5-3.358 7.5-7.5v-10a7.5 7.5 0 0 0-7.5-7.5zM325 240a7.5 7.5 0 0 0-7.5 7.5v10c0 4.142 3.357 7.5 7.5 7.5s7.5-3.358 7.5-7.5v-10a7.5 7.5 0 0 0-7.5-7.5zM325 200a7.5 7.5 0 0 0-7.5 7.5v10c0 4.142 3.357 7.5 7.5 7.5s7.5-3.358 7.5-7.5v-10a7.5 7.5 0 0 0-7.5-7.5zM145 345a7.5 7.5 0 0 0 7.5-7.5v-10c0-4.142-3.357-7.5-7.5-7.5s-7.5 3.358-7.5 7.5v10a7.5 7.5 0 0 0 7.5 7.5zM145 305a7.5 7.5 0 0 0 7.5-7.5v-10c0-4.142-3.357-7.5-7.5-7.5s-7.5 3.358-7.5 7.5v10a7.5 7.5 0 0 0 7.5 7.5zM145 265a7.5 7.5 0 0 0 7.5-7.5v-10c0-4.142-3.357-7.5-7.5-7.5s-7.5 3.358-7.5 7.5v10a7.5 7.5 0 0 0 7.5 7.5zM145 185a7.5 7.5 0 0 0 7.5-7.5v-10c0-4.142-3.357-7.5-7.5-7.5s-7.5 3.358-7.5 7.5v10a7.5 7.5 0 0 0 7.5 7.5zM145 225a7.5 7.5 0 0 0 7.5-7.5v-10c0-4.142-3.357-7.5-7.5-7.5s-7.5 3.358-7.5 7.5v10a7.5 7.5 0 0 0 7.5 7.5zM235 350a7.5 7.5 0 0 0-7.5 7.5v10c0 4.142 3.357 7.5 7.5 7.5s7.5-3.358 7.5-7.5v-10a7.5 7.5 0 0 0-7.5-7.5zM235 310a7.5 7.5 0 0 0-7.5 7.5v10c0 4.142 3.357 7.5 7.5 7.5s7.5-3.358 7.5-7.5v-10a7.5 7.5 0 0 0-7.5-7.5zM235 270a7.5 7.5 0 0 0-7.5 7.5v10c0 4.142 3.357 7.5 7.5 7.5s7.5-3.358 7.5-7.5v-10a7.5 7.5 0 0 0-7.5-7.5zM235 230a7.5 7.5 0 0 0-7.5 7.5v10c0 4.142 3.357 7.5 7.5 7.5s7.5-3.358 7.5-7.5v-10a7.5 7.5 0 0 0-7.5-7.5zM235 190a7.5 7.5 0 0 0-7.5 7.5v10c0 4.142 3.357 7.5 7.5 7.5s7.5-3.358 7.5-7.5v-10a7.5 7.5 0 0 0-7.5-7.5zM215 145h40c4.143 0 7.5-3.358 7.5-7.5s-3.357-7.5-7.5-7.5h-40c-4.143 0-7.5 3.358-7.5 7.5s3.357 7.5 7.5 7.5z'/%3E%3C/svg%3E");
            }

            .emoji-picker-react .emoji-categories {
                padding: 0 15px;
                display: flex;
                justify-content: space-between;
                box-sizing: border-box;
            }

            .emoji-picker-react .emoji-categories.inactive button,
            .emoji-picker-react .emoji-categories.inactive button.active,
            .emoji-picker-react .emoji-categories.inactive button:hover {
                opacity: 0.4;
                cursor: default;
            }

            .emoji-picker-react .emoji-categories button.active {
                opacity: 1;
            }

            .emoji-picker-react .emoji-categories button:hover {
                opacity: 0.7;
            }
        </style>
        <style>
            .emoji-picker-react .emoji {
                position: relative;
            }

            .emoji-picker-react .emoji.has-skin-variation button:before {
                content: '';
                display: block;
                width: 0;
                height: 0;
                right: -2px;
                bottom: 0px;
                position: absolute;
                border-left: 4px solid transparent;
                border-right: 4px solid transparent;
                border-bottom: 4px solid rgba(0, 0, 0, 0.1);
                transform: rotate(135deg);
                z-index: 1;
            }

            .emoji-picker-react .emoji.has-skin-variation button:hover:before,
            .emoji-picker-react .emoji.has-skin-variation button:focus-visible:before {
                border-bottom: 4px solid rgba(0, 0, 0, 0.4);
            }

            .emoji-picker-react .emoji button {
                display: flex;
                justify-content: center;
                align-items: center;
                color: inherit;
                border-radius: 5px;
                transition: 0.1s background;
                padding: 0;
                margin: 0;
            }

            .emoji-picker-react .emoji button:hover,
            .emoji-picker-react .emoji button:focus-visible {
                background-color: currentColor;
            }
        </style>
        <style>
            .emoji-picker-react .emoji-group {
                clear: both;
                padding: 0 15px;
                list-style: none;
                margin: 0;
                display: flex;
                flex-flow: row wrap;
                justify-content: space-between;
            }

            .emoji-picker-react .emoji-group:before {
                content: attr(data-display-name);
                color: #aaa;
                font-size: 14px;
                position: sticky;
                background: rgba(255, 255, 255, 0.95);
                width: 100%;
                z-index: 1;
                top: 0;
                text-transform: uppercase;
                line-height: 45px;
                font-weight: 700;
            }

            .emoji-picker-react .emoji-group:after {
                content: '';
                flex: 1000;
                order: 99999;
                flex-basis: 25px;
            }
        </style>
        <style>
            .emoji-picker-react input.emoji-search {
                width: calc(100% - 30px);
                margin-left: 15px;
                outline: none;
                box-shadow: none;
                padding: 10px;
                box-sizing: border-box;
                border: 1px solid #efefef;
                border-radius: 3px;
                transition: border 0.1s;
            }

            .emoji-picker-react input.emoji-search:focus {
                border: 1px solid #d6d6d6;
            }
        </style>
        <style>
            aside.emoji-picker-react {
                background: #fff;
                display: flex;
                flex-direction: column;
                height: 320px;
                width: 280px;
                font-family: sans-serif;
                border: 1px solid #efefef;
                border-radius: 5px;
                box-sizing: border-box;
                box-shadow: 0 5px 10px #efefef;
                overflow: hidden;
                position: relative;
            }

            aside.emoji-picker-react .content-wrapper {
                flex: 1;
                overflow-y: hidden;
                position: relative;
            }

            .emoji-picker-react .emoji-scroll-wrapper {
                overflow-y: scroll;
                position: relative;
                height: 100%;
                box-sizing: border-box;
            }

            aside.emoji-picker-react .content-wrapper:before {
                content: attr(data-name);
                color: #aaa;
                font-size: 11px;
                display: block;
                position: absolute;
                right: 15px;
                z-index: 10;
                line-height: 45px;
                max-height: 45px;
                overflow: hidden;
                max-width: 100px;
                text-overflow: ellipsis;
                text-align: right;
            }

            aside.emoji-picker-react button {
                border: none;
                cursor: pointer;
                outline: none;
                background: none;
            }
        </style>
        <link rel="stylesheet" type="text/css" href="css/9.cb7de3a7.chunk.css">
    </head>
    <body class="fixed-header" style="padding-top: 66px;">
        <div id="root">
            <%@include file="header.jsp" %>
            <%
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            %>
            <div class="hidden">
                <audio src="https://playerduo.net/static/media/notification-sound.805a8904.mp3"></audio>
                <audio src="https://playerduo.net/static/media/notification-group-sound.4c7ac55b.mp3"></audio>
                <audio src="https://playerduo.net/static/media/unconvinced.1de6c75d.mp3"></audio>
            </div>
            <div class="notifications-wrapper"></div>
            <div class="wrapper">
                <div class="setting__main row">
                    <div class="setting__main--menu col-lg-3 col-md-3 col-sm-12 col-xs-12">
                        <div class="menu">
                            <div class="menu__setting  panel-group">
                                <div class="menu__setting--main panel panel-default">
                                    <div class="panel-heading">
                                        <div class="panel-title">
                                            <a aria-expanded="true" class="" role="button" href="#">TÀI KHOẢN <i class="fas fa-chevron-down"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="panel-collapse collapse in" style="">
                                        <div class="panel-body">
                                            <div class="panel-group">
                                                <div class="menu__setting--sub panel panel-default">
                                                    <div class="panel-heading">
                                                        <div class="panel-title">
                                                            <i class="fas fa-user-tie"></i> Thông tin cá nhân
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="menu__setting--sub panel panel-default">
                                                    <div class="panel-heading">
                                                        <div class=" title-sub  panel-title">
                                                            <a aria-expanded="false" class="collapsed" role="button" href="#">
                                                                <i class="fas fa-cog"></i> Cài đặt <i class="fas fa-chevron-down"></i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <div class="panel-collapse collapse">
                                                        <div class="panel-body">
                                                            <div class="panel-group">
                                                                <div class="menu__setting--last panel panel-default">
                                                                    <div class="panel-heading">
                                                                        <div class="panel-title">Email</div>
                                                                    </div>
                                                                </div>
                                                                <div class="menu__setting--last panel panel-default">
                                                                    <div class="panel-heading">
                                                                        <div class="panel-title">Tài khoản và mật khẩu</div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div><%if(u.getRole().equalsIgnoreCase("mentor")) {%> <div class="menu__setting--sub panel panel-default">
                                                    <div class="panel-heading">
                                                        <div class="  panel-title">
                                                            <i class="fas fa-user-lock"></i> Thông Tin CV
                                                        </div>
                                                    </div>
                                                </div><div class="menu__setting--sub panel panel-default">
                                                    <div class="panel-heading">
                                                        <div class="active  panel-title">
                                                            <i class="fas fa-user-lock"></i> Thống Kê Request
                                                        </div>
                                                    </div>
                                                </div><%} else {%><div class="menu__setting--sub panel panel-default">
                                                    <div class="panel-heading">
                                                        <div class="active  panel-title">
                                                            <i class="fas fa-user-lock"></i> Thống Kê Request
                                                        </div>
                                                    </div>
                                                </div>
                                                <%}%>
                                                <div class="menu__setting--sub panel panel-default">
                                                    <div class="panel-heading">
                                                        <div class="title-sub  panel-title">
                                                            <a aria-expanded="false" class="collapsed" role="button" href="#">
                                                                <i class="fas fa-history"></i> Lịch sử giao dịch 
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <div class="panel-collapse collapse">
                                                        <div class="panel-body">
                                                            <div class="panel-group">
                                                                <div class="menu__setting--last panel panel-default">
                                                                    <div class="panel-heading">
                                                                        <div class="panel-title">Lịch sử donate</div>
                                                                    </div>
                                                                </div>
                                                                <div class="menu__setting--last panel panel-default">
                                                                    <div class="panel-heading">
                                                                        <div class="panel-title">Lịch sử duo</div>
                                                                    </div>
                                                                </div>
                                                                <div class="menu__setting--last panel panel-default">
                                                                    <div class="panel-heading">
                                                                        <div class="panel-title">Lịch sử tạo code</div>
                                                                    </div>
                                                                </div>
                                                                <div class="menu__setting--last panel panel-default">
                                                                    <div class="panel-heading">
                                                                        <div class="panel-title">Biến động số dư</div>
                                                                    </div>
                                                                </div>
                                                                <div class="menu__setting--last panel panel-default">
                                                                    <div class="panel-heading">
                                                                        <div class="panel-title">Lịch sử mua thẻ</div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="menu__setting--sub panel panel-default">
                                                    <div class="panel-heading">
                                                        <div class="  panel-title">
                                                            <i class="fas fa-credit-card"></i> Thanh toán 
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="menu__setting--sub panel panel-default">
                                                    <div class="panel-heading">
                                                        <div class="  panel-title">
                                                            <i class="fas fa-wallet"></i> Ví 
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="btn-drawer-setting visible-xs">
                                <i class="fas fa-chevron-right"></i>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
                        <div class="aside">
                            <h3>Thống Kê Request</h3>
                            <%  if(u.getRole().equalsIgnoreCase("mentee")) {
                                MenteeStatistic ms = (MenteeStatistic)request.getAttribute("mstatistic");%>
                                <div class="col-md-6">
                                    <div class="fieldGroup changepass--fieldGroup">
                                        <p class="control-label">Tổng số Request đã gửi: <span style="color:black; font-weight: bold; text-transform: none"><%=ms.getTotalRequest()%> requests</span></p>
                                    </div>
                                    <hr>
                                    <div class="fieldGroup changepass--fieldGroup">
                                        <p class="control-label">Tổng số Request bị từ chối: <span style="color:black; font-weight: bold; text-transform: none"><%=ms.getRejectedRequest()%> requests</span></p>
                                    </div>
                                    <hr>
                                    <div class="fieldGroup changepass--fieldGroup">
                                        <p class="control-label">Tổng số Request được chấp thuận: <span style="color:black; font-weight: bold; text-transform: none"><%=ms.getAcceptedRequest()%> requests</span></p>
                                    </div>
                                    <hr>
                                    <div class="fieldGroup changepass--fieldGroup">
                                        <p class="control-label">Tổng thời gian học: <span style="color:black; font-weight: bold; text-transform: none"><%=ms.getTotalHours()%> giờ</span></p>
                                    </div>
                                    <hr>
                                    <div class="fieldGroup changepass--fieldGroup">
                                        <p class="control-label">Tổng số Mentor yêu cầu: <span style="color:black; font-weight: bold; text-transform: none"><%=ms.getTotalMentor()%> mentors</span></p>
                                    </div>
                                </div>
                                    <%} else {
                                    MentorStatistic ms = (MentorStatistic)request.getAttribute("mstatistic");
                                    %>
                                <div class="col-md-6">
                                    <div class="fieldGroup changepass--fieldGroup">
                                        <p class="control-label">Tổng số Request nhận được: <span style="color:black; font-weight: bold; text-transform: none"><%=ms.getInvitedRequest()%> requests</span></p>
                                    </div>
                                    <hr>
                                    <div class="fieldGroup changepass--fieldGroup">
                                        <p class="control-label">Tổng số Request đã từ chối: <span style="color:black; font-weight: bold; text-transform: none"><%=ms.getRejectedRequest()%> requests</span></p>
                                    </div>
                                    <hr>
                                    <div class="fieldGroup changepass--fieldGroup">
                                        <p class="control-label">Tỉ lệ từ chối Request: <span style="color:black; font-weight: bold; text-transform: none"><%=ms.getRejectPercent() * 100%> %</span></p>
                                    </div>
                                    <hr>
                                    <div class="fieldGroup changepass--fieldGroup">
                                        <p class="control-label">Tổng số Request đã chấp thuận: <span style="color:black; font-weight: bold; text-transform: none"><%=ms.getAccepedRequest()%> requests</span></p>
                                    </div>
                                    <hr>
                                    <div class="fieldGroup changepass--fieldGroup">
                                        <p class="control-label">Đánh Giá từ học viên: <span style="color:black; font-weight: bold; text-transform: none"><%=ms.getRating()%> sao</span></p>
                                    </div>
                                    <hr>
                                    <div class="fieldGroup changepass--fieldGroup">
                                        <p class="control-label">Tỉ lệ hoàn thành request: <span style="color:black; font-weight: bold; text-transform: none"><%=ms.getCompletePercent()*100%> %</span></p>
                                    </div>
                                </div>
                                    <% }%>
                            <%if(request.getAttribute("error") != null) {%>
                            <span class="err-message"><%=(String)request.getAttribute("error")%></span>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>

            let cog = document.getElementsByClassName('fas fa-cog')[0].parentNode.children[1];
            let collapse = cog.parentNode.parentNode.parentNode.parentNode.children[1];
            document.getElementsByClassName('fas fa-cog')[0].parentNode.onclick = function () {
                if (cog.classList.contains("fa-chevron-right")) {
                    cog.classList.add("fa-chevron-down");
                    cog.classList.remove("fa-chevron-right");
                    collapse.classList.remove("collapse");
                    collapse.classList.add("collapsing");
                    setTimeout(function () {
                        collapse.style = "height: 72px;";
                    }, 1);
                    setTimeout(function () {
                        collapse.classList.remove("collapsing");
                        collapse.classList.add("collapse");
                        collapse.style = "";
                        collapse.classList.add("in");
                    }, 300);
                } else {
                    cog.classList.remove("fa-chevron-down");
                    cog.classList.add("fa-chevron-right");
                    collapse.style = "height: 72px;";
                    collapse.classList.remove("collapse");
                    collapse.classList.add("collapsing");
                    setTimeout(function () {
                        collapse.style = "height: 0px;";
                    }, 1);
                    setTimeout(function () {
                        collapse.classList.remove("collapsing");
                        collapse.classList.add("collapse");
                        collapse.style = "height: 0px;";
                        collapse.classList.remove("in");
                    }, 300);
                }
            }
            function isValidUrl(string) {
                try {
                    new URL(string);
                    return true;
                } catch (err) {
                    return false;
                }
            }

            document.getElementsByClassName('menu__setting--last panel panel-default')[0].onclick = function () {
                window.location.href = "email";
            };
            document.getElementsByClassName('menu__setting--last panel panel-default')[1].onclick = function () {
                window.location.href = "setting";
            };
            document.getElementsByClassName('menu__setting--sub panel panel-default')[0].onclick = function () {
                window.location.href = "profile";
            };
            <%if(u.getRole().equalsIgnoreCase("mentor")) {%>
            document.getElementsByClassName('menu__setting--sub panel panel-default')[2].onclick = function () {
                window.location.href = "cv";
            };
            document.getElementsByClassName('menu__setting--sub panel panel-default')[3].onclick = function () {
                window.location.href = "statistic";
            };
            document.getElementsByClassName('menu__setting--sub panel panel-default')[5].onclick = function () {
                window.location.href = "bank";
            };
            document.getElementsByClassName('menu__setting--sub panel panel-default')[6].onclick = function () {
                window.location.href = "wallet";
            };
            <%} else {%>
            document.getElementsByClassName('menu__setting--sub panel panel-default')[2].onclick = function () {
                window.location.href = "statistic";
            };
            document.getElementsByClassName('menu__setting--sub panel panel-default')[4].onclick = function () {
                window.location.href = "bank";
            };
            document.getElementsByClassName('menu__setting--sub panel panel-default')[5].onclick = function () {
                window.location.href = "wallet";
            };
                <%}%>
        </script>
    </body>
</html>
