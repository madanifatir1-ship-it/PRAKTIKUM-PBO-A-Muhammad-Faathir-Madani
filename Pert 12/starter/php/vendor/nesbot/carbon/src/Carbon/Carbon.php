<?php

declare(strict_types=1);

namespace Carbon;

use DateTimeImmutable;
use DateTimeZone;

class Carbon extends DateTimeImmutable
{
    public static function now(DateTimeZone|string|null $tz = null): static
    {
        $zone = is_string($tz) ? new DateTimeZone($tz) : $tz;
        return new static('now', $zone);
    }

    public static function parse(string $time = 'now', DateTimeZone|string|null $tz = null): static
    {
        $zone = is_string($tz) ? new DateTimeZone($tz) : $tz;
        return new static($time, $zone);
    }

    public static function today(DateTimeZone|string|null $tz = null): static
    {
        return static::now($tz)->setTime(0, 0, 0);
    }

    public static function tomorrow(DateTimeZone|string|null $tz = null): static
    {
        return static::today($tz)->modify('+1 day');
    }

    public static function yesterday(DateTimeZone|string|null $tz = null): static
    {
        return static::today($tz)->modify('-1 day');
    }

    public function addDays(int $days): static
    {
        return $this->modify(($days >= 0 ? '+' : '') . $days . ' days');
    }

    public function subDays(int $days): static
    {
        return $this->addDays(-$days);
    }

    public function diffInDays(DateTimeImmutable $other, bool $absolute = true): int
    {
        $diff = $this->diff($other);
        $days = (int) $diff->format('%a');
        return $absolute ? $days : ($diff->invert ? -$days : $days);
    }

    public function toDateString(): string
    {
        return $this->format('Y-m-d');
    }

    public function toDateTimeString(): string
    {
        return $this->format('Y-m-d H:i:s');
    }
}
