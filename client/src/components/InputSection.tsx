import React from "react";

interface InputSectionProps {
  query: string;
  setQuery: (value: string) => void;
  loading: boolean;
  handleSubmit: (e: React.FormEvent) => void;
}

const InputSection: React.FC<InputSectionProps> = ({
  query,
  setQuery,
  loading,
  handleSubmit,
}) => {
  return (
    <div className="flex-1">
      <h1 className="text-4xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-indigo-500 to-purple-500 text-center mb-8">
        BookBuddy AI
      </h1>
      <form
        onSubmit={handleSubmit}
        className="flex flex-col gap-4 items-center"
      >
        <input
          type="text"
          className="rounded-full border-2 border-indigo-400 px-6 py-3 text-lg focus:outline-none transition shadow-md placeholder:text-indigo-400 bg-indigo-50 w-full"
          placeholder="What are you in the mood for?"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          required
        />
        <button
          type="submit"
          className="bg-gradient-to-r from-indigo-500 to-purple-500 text-white font-bold py-2 px-6 rounded-full shadow-lg hover:scale-105 transition-transform duration-200 disabled:opacity-50"
          disabled={loading}
        >
          <span className={loading ? "animate-spin" : ""}>✨</span>
          {loading ? "Searching..." : "Find the perfect book"}
        </button>
      </form>
    </div>
  );
};

export default InputSection;
