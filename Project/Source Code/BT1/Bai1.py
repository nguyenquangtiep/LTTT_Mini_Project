import numpy as np
from scipy.stats import entropy

def xacSuatDauVao():
    M = int(input("Nhap so hang M: "))
    N = int(input("Nhap so cot N: "))

    xacSuat = []

    for i in range(M):
        row = []
        for j in range(N):
            while True:
                xacSuat_str = input(f"Nhap xac suat P({i}, {j}): ")

                # Kiem tra neu gia tri nhap vao la so thap phan
                try:
                    probability = float(xacSuat_str)
                    if probability >= 0:
                        break
                    else:
                        print("Xac suat khong duoc am. Vui long nhap lai.")
                except ValueError:
                    # Neu gia tri nhap vao khong phai la so thap phan, thu kiem tra xem co phai la dang phan so khong
                    try:
                        numerator, denominator = map(int, xacSuat_str.split('/'))
                        if numerator >= 0 and denominator > 0:
                            probability = numerator / denominator
                            break
                        else:
                            print("Xac suat khong hop le, vui long nhap lai.")
                    except ValueError:
                        print("Gia tri khong hop le, vui long nhap lai.")

            row.append(probability)

        xacSuat.append(row)

    return xacSuat

def print_maTranXacSuat(matrix):
    print("\nMa tran ket hop P(x,y)")
    for row in matrix:
        print(',\t'.join(str(prob) for prob in row))
def tinhDoLechKullbackLeibler(xacSuat_x, xacSuat_y):
    return entropy(xacSuat_x, xacSuat_y, base=2)

def main():
    # Nhap ma tran xac suat ket hop P(x, y)
    maTranXacSuat = xacSuatDauVao()
    print_maTranXacSuat(maTranXacSuat)

    joint_probabilities = np.array(maTranXacSuat)

    # Tinh xac suat bien
    marginal_probabilities_y = joint_probabilities.sum(axis=1)
    marginal_probabilities_x = joint_probabilities.sum(axis=0)

    # Tinh entropy
    entropy_x = entropy(marginal_probabilities_x, base=2)
    entropy_y = entropy(marginal_probabilities_y, base=2)
    
    # Tinh joint entropy:
    joint_entropy = entropy(joint_probabilities.flatten(), base=2)
    
    # Tinh conditional entropy
    conditional_entropy_x_given_y = joint_entropy - entropy_y
    conditional_entropy_y_given_x = joint_entropy - entropy_x

    # Tinh mutual information = H(Y)-H(Y|X)
    mutual_information_1 = entropy(marginal_probabilities_y, base=2)+entropy(marginal_probabilities_x,base=2) - joint_entropy
    
    # Tinh D(P(x)||P(y)) va D(P(y)||P(x))
    kl_divergence_xy = tinhDoLechKullbackLeibler(marginal_probabilities_x, marginal_probabilities_y)
    kl_divergence_yx = tinhDoLechKullbackLeibler(marginal_probabilities_y, marginal_probabilities_x)

    # Hien thi cac ket qua
    print("\nHien thi ket qua:")
    print(f"H(X): {entropy_x}")
    print(f"H(Y): {entropy_y}")
    print(f"H(X | Y): {conditional_entropy_x_given_y}")
    print(f"H(Y | X): {conditional_entropy_y_given_x}")
    print(f"H(X, Y): {joint_entropy}")
    print(f"H(Y) - H(Y | X): {entropy_y - conditional_entropy_y_given_x}")
    print(f"I(X; Y): {mutual_information_1}")
    print(f"D(P(x)||P(y)): {kl_divergence_xy}")
    print(f"D(P(y)||P(x)): {kl_divergence_yx}")

if __name__ == "__main__":
    main()
