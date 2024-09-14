/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["../resources/templates/**/*.{html,js}"],
  theme: {
    corePlugins: {
      preflight: false,
    },
    fontFamily: {
      sans: ['arial', 'sans-serif'],
    },
    extend: {
        colors: {
            'verylightblue': '#F5F9FE',
            'mossgreen': '#919653',
            'bluegray': '#6B5B9A',
            'blueyonder': '#537796',
            'pine': '#5B9A8A',
            'link': '#4d4de7',
            'linkvisited': '#912faf',
            'linkactive': '#FF0000',

            'tetrad': '#539679',


        }
    },
  },
  plugins: [],

}

