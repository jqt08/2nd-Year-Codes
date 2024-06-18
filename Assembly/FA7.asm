section .data
    result db "The factorial of 3 is: ", 0
    newline db 10, 0
    factorial_result db "6", 0

section .text
    global _start

_start:
    mov eax, 1      ; EAX will hold the result of factorial
    mov ebx, 3      ; EBX will hold the current number to multiply

    call factorial

    ; Convert the result in EAX to ASCII
    add eax, '0'
    mov [factorial_result], al

    ; Print the message
    mov eax, 4      ; sys_write
    mov ebx, 1      ; file descriptor (stdout)
    mov ecx, result ; pointer to message
    mov edx, 22     ; length of message
    int 0x80

    ; Print the factorial result
    mov eax, 4      ; sys_write
    mov ebx, 1      ; file descriptor (stdout)
    mov ecx, factorial_result ; pointer to factorial result
    mov edx, 1      ; length of result
    int 0x80

    ; Print newline
    mov eax, 4      ; sys_write
    mov ebx, 1      ; file descriptor (stdout)
    mov ecx, newline ; pointer to newline
    mov edx, 1      ; length of newline
    int 0x80

    ; Exit program
    mov eax, 1      ; sys_exit
    xor ebx, ebx    ; status 0
    int 0x80

factorial:
    cmp ebx, 1
    jle .done
.loop:
    mul ebx         ; EAX = EAX * EBX
    dec ebx         ; decrement EBX
    cmp ebx, 1
    jne .loop       ; if EBX is not one, repeat
.done:
    ret
