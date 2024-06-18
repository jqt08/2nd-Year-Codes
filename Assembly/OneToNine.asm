section .data
    num db '0'
    newline db 10

section .text
global _start

_start:
    mov rcx, 10       ; Use rcx for the loop counter in 64-bit mode
    mov rax, '1'      ; Initialize rax with ASCII '1'

eleven:
    mov [num], al     ; Store the current ASCII value in num
    mov rax, 1        ; syscall number for sys_write
    mov rdi, 1        ; file descriptor for stdout
    mov rsi, num      ; pointer to the num buffer
    mov rdx, 1        ; number of bytes to write
    syscall           ; make the system call

    mov al, [num]     ; Load the ASCII value from num
    sub al, '0'       ; Convert ASCII to integer
    inc al            ; Increment the integer
    add al, '0'       ; Convert back to ASCII

    loop eleven       ; Decrement rcx and jump if rcx != 0

    ; Print newline
    mov rax, 1        ; syscall number for sys_write
    mov rdi, 1        ; file descriptor for stdout
    mov rsi, newline  ; pointer to the newline buffer
    mov rdx, 1        ; number of bytes to write
    syscall           ; make the system call

    ; Exit program
    mov rax, 60       ; syscall number for sys_exit
    xor rdi, rdi      ; status 0
    syscall           ; make the system call

section .bss
    num resb 1
