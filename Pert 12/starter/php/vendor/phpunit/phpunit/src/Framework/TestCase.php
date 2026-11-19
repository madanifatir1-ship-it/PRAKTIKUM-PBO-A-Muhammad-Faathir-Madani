<?php

declare(strict_types=1);

namespace PHPUnit\Framework;

abstract class TestCase extends Assert
{
    private ?string $expectedException = null;
    private ?string $expectedExceptionMessage = null;

    protected function setUp(): void
    {
    }

    protected function tearDown(): void
    {
    }

    public function expectException(string $exception): void
    {
        $this->expectedException = $exception;
    }

    public function expectExceptionMessage(string $message): void
    {
        $this->expectedExceptionMessage = $message;
    }

    public function getExpectedException(): ?string
    {
        return $this->expectedException;
    }

    public function getExpectedExceptionMessage(): ?string
    {
        return $this->expectedExceptionMessage;
    }

    public function resetExpectations(): void
    {
        $this->expectedException = null;
        $this->expectedExceptionMessage = null;
    }
}
