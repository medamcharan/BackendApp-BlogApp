import os

# Define the root directory and the output file
root_dir = r'./'
output_file_path = os.path.join(root_dir, 'merged_output.txt')

# Initialize a list to collect file paths
file_paths = []

# Walk through the directory and collect all Java file paths
for dirpath, _, filenames in os.walk(root_dir):
    for filename in filenames:
        if filename.endswith('.java'):
            file_paths.append(os.path.join(dirpath, filename))

# Open the output file in write mode
with open(output_file_path, 'w') as outfile:
    for file_path in file_paths:
        with open(file_path, 'r') as infile:
            outfile.write(f'Contents of {file_path}:\n')
            outfile.write(infile.read())
            outfile.write('\n\n')

print(f'Merged {len(file_paths)} files into {output_file_path}')
