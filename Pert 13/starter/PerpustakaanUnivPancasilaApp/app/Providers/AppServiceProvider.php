<?php

namespace App\Providers;

use App\Repositories\BookRepository;
use App\Repositories\EloquentBookRepository;
use App\Repositories\InMemoryBookRepository;
use Illuminate\Support\ServiceProvider;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     */
    public function register(): void
    {
        $this->app->bind(BookRepository::class, EloquentBookRepository::class);
        // Untuk uji Service Container (Langkah 6), ganti ke baris di bawah tanpa mengubah BukuController:
        // $this->app->bind(BookRepository::class, InMemoryBookRepository::class);
    }

    /**
     * Bootstrap any application services.
     */
    public function boot(): void
    {
        //
    }
}