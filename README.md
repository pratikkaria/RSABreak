# RSABreak

As it is known, RSA (Rivest, Shamir, and Adelman) is one of the strongest encryption algorithm of the present day. The thing that makes RSA such a strong encryption algorithm is that in RSA if Person C intercepts your message to Person B, they already know the encryption key (exponent e, modulus n).  However, what he/she doesn't have is the decryption exponent d.  Since you encrypted your message with Person B's encryption key, only Person B has the decryption key (exponent d, modulus n) to decrypt it.  Person C is only missing one piece of information, exponent d, which turns out to be the hardest piece of information to find. As we know that to find decryption key we need to find phi(n) which means we have to do prime factorization. Now the problem with prime factorization is that it is a Hard Problem ( till now it is not possible to solve it in polynomial time) . So the large the length of n, the more time it takes for the system to find phi(n) [It might even take years] . So in this solution, I propose a different way of getting the message. Instead of trying to find phi(n) and then get decrpytion key to decrypt the message, we would decrypt the message using exponent e, modulus n and cipher text c.


Getting Started

The formula used in this algorithm is derived as follows :-

c = (m^e)mod n 
=>(m^e) - n*t = c
=> m^e = c + n*t
=> m = ((c + n*t)^(1/e))

Here, 
m = The original message
c = cipher text
n = modulus n
e = exponent d (encryption key)
t = Any integer

The message will be found once the value of the m becomes an integer. The first integer value of m is the required message. As the process can get slow once the value of e increases so to speed up the process an additional feature is added. Once the integer value of message for 2 consecutive prime values of t is same , we direct jump to the next prime number and find out the new value of m thereby reducing the number of iterations. 

Example
 
Sample Output:-

Enter the encrypion key 
3
Enter the value of n 
3127
Enter the cipher text 
1394
89.000000          225
TIME: 11066

Verification:-
So in the above example, the required message is 89 and the value of t is 225. We can verify this by putting t in the formula.
Here n=3127, c=1394 and encryption key=3

m = (3127*225 + 1394)^(1/3)
=> m = 89

Several other examples have been tested and led to accurate solution. However for large value of encryption key e, the algorithm still responds slowly.
