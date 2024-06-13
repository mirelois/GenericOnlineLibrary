import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BookshelfView from '../views/BookshelfView.vue'
import BookDetailView from '../views/BookDetailView.vue'
import BooksMenuView from '../views/BooksMenuView.vue'
import SignupPage from '@/views/SignupPage.vue'
import ProfilePageView from '@/views/ProfilePageView.vue'
import LoginPage from '@/views/LoginPage.vue'
import BookCategoryView from '../views/BookCategoryView.vue' 
import NotificationsView from '@/views/NotificationsView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/bookshelves/:bookshelfname',
      name: 'bookshelves',
      component: BookshelfView
    },
    {
      path: '/bookmenu',
      name: 'booksmenu',
      component: BooksMenuView
    },
    {
      path: '/books/:bookisbn',
      name: 'bookdetail',
      component: BookDetailView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/profile',
      name: 'profile',
      component: ProfilePageView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPage
    },
    {
      path: '/notifications',
      name: 'notifications',
      component: NotificationsView 
    },
    {
      path: '/books/?categoria=:category',
      name: 'bookCategoria',
      component: BookCategoryView,
    },
    {
      path: '/signup',
      name: 'signup',
      component: SignupPage
    }
  ]
})

export default router