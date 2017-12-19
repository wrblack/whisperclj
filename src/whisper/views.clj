(ns whisper.views
  (:require [hiccup.page :as page]
            [hiccup.core :refer :all]
            [clojure.string :as str]))

(defn gen-page-head [title]
  [:head
   [:title (str "Whisper | " title)]
   [:link {:href "https://cdnjs.cloudflare.com/ajax/libs/bulma/0.6.1/css/bulma.min.css"
           :rel :stylesheet}]
   [:link {:href "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
           :rel :stylesheet}]
   (page/include-css "/css/styles.css")])

(def header-links
  [:nav.navbar.is-transparent
   [:div.navbar-brand
    [:a.navbar-item {:href "/"}
     [:h1 "WHiSPER"]
     [:div.navbar-burger.burger {:data-target "site-navbar"}
      [:span]
      [:span]
      [:span]]]]
   [:div#site-navbar.navbar-menu
    [:div.navbar-start
     [:a.navbar-item {:href "/"} [:i.fa.fa-home] "&nbsp; Home"]
     [:a.navbar-item {:href "/whispers"} [:i.fa.fa-comments-o] "&nbsp; Whispers"]
     [:a.navbar-item {:href "/about"} [:i.fa.fa-question-circle-o] "&nbsp; About"]]]])

(def index-page
  (page/html5
    (gen-page-head "Home")
    header-links
    [:section.hero
     [:div.hero-body
      [:div.container
       [:h1.title "Whisper"]
       [:h2.subtitle "A place to keep your words."]
       [:p "Post a whisper:"]
       [:div.columns
        [:div.column
         [:form {:action "/", :method "POST", :class "form"}
          [:div.field
            [:div.control
              [:input.input {:type "text" :placeholder "Your pseudonym" :name :pseudonym}]]]
          [:div.field
           [:div.control
            [:textarea.textarea {:name :whisper-body :placeholder "Your whisper here."}]]]
          [:div.field
           [:div.control
            [:button.button.is-link {:type "submit"} "Submit"]]]]]
        [:div.column]]]]]))



(defn whispers-page [whispers]
  (page/html5
    (gen-page-head "Whispers List")
    header-links
    [:div.container
      [:h1.title "Whispers"]
     (if (seq whispers)
       [:div.container
        (for [w whispers]
          [:div.container
            [:p.title.is-3 "From " (h (:pseudonym w))]
            [:p.subtitle.is-5 "Who whispers... " (h (:whisper_body w))]
            [:br]])]
       [:div.container
        [:p "No whispers... yet"]])]))

(def about-page
  (page/html5
    (gen-page-head "FAQ")
    header-links
    [:div.container
     [:h1.title "About Whisper"]
     [:p "Q. Well, what is Whisper about?"]
     [:p "A. A place where you can post messages anonymously. Only pseudonyms and messages are saved."]
     [:br]
     [:p "Q. Why was Whisiper created?"]
     [:p "A. As an exercise in Clojure web development!"]
     [:br]
     [:p "Q. Where can I learn more about this amazing language Clojure?"]
     [:p "A. Here, let me " [:a {:href "http://lmgtfy.com/?q=clojure+language"} "Google"] " that for you."]]))

